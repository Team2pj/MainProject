import cv2
import numpy as np
import time
import pymysql
import os
import tensorflow as tf
from twilio.rest import Client

# db연동
db = pymysql.connect(
    user='root',
    passwd='1234',
    host='127.0.0.1',
    db='example',
    charset='utf8'
)

cursor = db.cursor()
sql = "SELECT * FROM proj;"
cursor.execute(sql)
result = cursor.fetchall()
result

## pc캠
# VideoSignal = cv2.VideoCapture(0)

## 서버
VideoSignal = cv2.VideoCapture('rtsp://admin:asd1639fF@192.168.1.10/profile5/media.smp')

# 동영상 불러오기
#VideoSignal = cv2.VideoCapture('assault.mp4')
#fourcc = cv2.VideoWriter_fourcc(* 'XVID')

# YOLO 가중치 파일과 CFG 파일 로드
YOLO_net = cv2.dnn.readNet("yolo-obj_4000.weights", "yolo-obj.cfg")

# YOLO NETWORK 재구성
classes = []

with open("yolo.names", "r") as f:
    classes = [line.strip() for line in f.readlines()]
layer_names = YOLO_net.getLayerNames()
output_layers = [layer_names[i[0] - 1] for i in YOLO_net.getUnconnectedOutLayers()]

YOLO_net.setPreferableBackend(cv2.dnn.DNN_BACKEND_CUDA)
YOLO_net.setPreferableTarget(cv2.dnn.DNN_TARGET_CUDA)

while True:
    # 웹캠 프레임
    ret, frame = VideoSignal.read()
    h, w, c = frame.shape

    #YOLO 입력
    blob = cv2.dnn.blobFromImage(frame, 0.00392, (160, 160), (0, 0, 0),
    True, crop=False)
    YOLO_net.setInput(blob)
    outs = YOLO_net.forward(output_layers)

    class_ids = []
    confidences = []
    boxes = []

    for out in outs:
        for detection in out:
            scores = detection[5:]
            class_id = np.argmax(scores)
            confidence = scores[class_id]

            if confidence > 0.8:
                # Object detected
                center_x = int(detection[0] * w)
                center_y = int(detection[1] * h)
                dw = int(detection[2] * w)
                dh = int(detection[3] * h)

                # Rectangle coordinate
                x = int(center_x - dw / 2)
                y = int(center_y - dh / 2)
                boxes.append([x, y, dw, dh])
                confidences.append(float(confidence))
                class_ids.append(class_id)

    indexes = cv2.dnn.NMSBoxes(boxes, confidences, 0.45, 0.4)

    for i in range(len(boxes)):
        if i in indexes:
            x, y, w, h = boxes[i]
            label = str(classes[class_ids[i]])
            score = confidences[i]

            # 경계상자와 클래스 정보 이미지에 입력
            #cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 0, 255), 5)
            #cv2.putText(frame, label, (x, y - 20), cv2.FONT_ITALIC, 0.5,
            #(255, 255, 255), 1)

            if label == "assault" and score > 0.9:
                    f = '%Y-%m-%d %H:%M:%S'
                    f1 = time.strftime(f)
                    cursor.execute("INSERT IGNORE INTO record_entity(inference_time, inference_type) VALUES(%s,%s)",(f1,"assault"))
                    db.commit()
                    print("db입력완료")

                    #SMS
                    account_sid = 'AC670b21b8640955962ed38d9a4407393b'
                    auth_token = '8bd4b6f5b7228bef6d375bff7d4a1d6e'
                    client = Client(account_sid, auth_token)
                    message = client.messages.create(
                            to="+8201029921639",
                            from_="+18456228005",
                            body="폭행 의심 현상이 발생하였습니다. 웹페이지에 접속하여 영상을 확인해주세요."
                       )
                    print(message.sid)
                    #time.sleep(10)

    cv2.imshow("YOLOv3", frame)

    # print(score)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

VideoSignal.release()
cv2.destroyAllWindows()
