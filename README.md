# 📚 API 명세 : BE-no2-assignment

**Base URL**: `http://localhost:8080`

---

## 📌 Schedule API

### 📤 일정 생성

- **URL**: `POST /api/v1.2.0/schedules/create`
- **Request Body**:
```json
{
  "title": "schedule1",
  "password": 1234,
  "content": "final Exam",
  "userId": 1
}
```
- **Response Body**:
```json
{
  "code": 0,
  "message": "create schedule success",
  "value": 1
}
```
---

### 📥 단건 일정 조회

- **URL**: `GET /api/v1.2.0/schedules/{id}`  
  예시: `/api/v1.2.0/schedules/3`

- **Response Body**:
```json
{
  "code": 0,
  "message": "find schedule success",
  "value": {
    "id": 3,
    "title": "schedule1",
    "content": "final Exam",
    "writerName": "kim",
    "createdAt": "2025-05-25T22:20:00.273638",
    "updatedAt": "2025-05-25T22:20:00.273638"
  }
}
```
---

### 📋 전체 일정 조회

- **URL**: `GET /api/v1.2.0/schedules`  
  예시: `/api/v1.2.0/schedules?writerId=1&updatedAt=2025-05-25T00:00:00&page=0&size=10`
- **Response Body**:
```json
{
  "code": 0,
  "message": "find schedule list success",
  "value": [
   {
    "code": 0,
    "message": "find schedule list success",
    "value": {
        "content": [
            {
                "id": 20,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:06:26.633852",
                "updatedAt": "2025-05-26T00:06:26.633852"
            },
            {
                "id": 19,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:06:25.807634",
                "updatedAt": "2025-05-26T00:06:25.807634"
            },
            {
                "id": 18,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:06:25.065996",
                "updatedAt": "2025-05-26T00:06:25.065996"
            },
            {
                "id": 17,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:06:24.205559",
                "updatedAt": "2025-05-26T00:06:24.205559"
            },
            {
                "id": 16,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:02:54.719007",
                "updatedAt": "2025-05-26T00:02:54.719007"
            },
            {
                "id": 15,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:02:53.528996",
                "updatedAt": "2025-05-26T00:02:53.528996"
            },
            {
                "id": 14,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:02:52.535771",
                "updatedAt": "2025-05-26T00:02:52.535771"
            },
            {
                "id": 13,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:02:43.589726",
                "updatedAt": "2025-05-26T00:02:43.589726"
            },
            {
                "id": 12,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:02:42.666393",
                "updatedAt": "2025-05-26T00:02:42.666393"
            },
            {
                "id": 11,
                "title": "schedule1",
                "content": "final Exam",
                "writerName": "kim",
                "createdAt": "2025-05-26T00:02:41.739429",
                "updatedAt": "2025-05-26T00:02:41.739429"
            }
        ],
        "pageable": {
            "pageNumber": 0,
            "pageSize": 10,
            "sort": {
                "empty": false,
                "unsorted": false,
                "sorted": true
            },
            "offset": 0,
            "unpaged": false,
            "paged": true
        },
        "last": false,
        "totalPages": 2,
        "totalElements": 15,
        "first": true,
        "numberOfElements": 10,
        "size": 10,
        "number": 0,
        "sort": {
            "empty": false,
            "unsorted": false,
            "sorted": true
        },
        "empty": false
    }
}
  ]
}
```
---

### ✏️ 일정 수정

- **URL**: `PUT /api/v1.2.0/schedules/{id}`  
  예시: `/api/v1.2.0/schedules/2`
- **Request Body**:
```json
{
  "password": 1234,
  "content": "MidTerm project",
  "newUserId": 3
}
```
- **Response Body**:
```json
{
  "code": 0,
  "message": "update schedule success",
  "value": null
}
```

---

### ❌ 일정 삭제

- **URL**: `DELETE /api/v1.2.0/schedules/{id}`  
  예시: `/api/v1.2.0/schedules/2`
- **Response Body**:
```json
{
  "code": 0,
  "message": "delete schedule success",
  "value": null
}
```

---

## 👤 User API

### 🆕 유저 생성

- **URL**: `POST /api/v1.2.0/users/create`
- **Request Body**:
```json
{
  "name": "kim",
  "email": "kim1568@gmail.com"
}
```
- **Response Body**:
```json
{
  "code": 0,
  "message": "create user success",
  "value": 3
}
```
---

## 🔁 버전별 추가 API (v1.1.0)

버전 1.1.0에서도 동일한 구조의 API가 존재합니다. 경로만 다르며 요청 형식은 같습니다:

- `POST /api/v1.1.0/schedules/create`
- `GET /api/v1.1.0/schedules/{id}`
- `GET /api/v1.1.0/schedules`
- `PUT /api/v1.1.0/schedules/{id}`
- `DELETE /api/v1.1.0/schedules/{id}`

---
- `PUT /api/v1.1.0/schedules/{id}`의 경우 `v1.2.0`과 다르게 `writerName`을 requestBody의 필드로 받음
- **URL**: `POST /api/v1.1.0/schedules/{id}`  
  예시:  `/api/v1.1.0/schedules/3`
- **Request Body**:
```json
{
  "password":1234,
  "content":"final Exam222",
  "writerName":"lwjddd22222"
}
```

# 💡 ERD Diagram
![image](https://github.com/user-attachments/assets/126ceaf1-34e1-47f4-8d2a-1bfaa46d17b0)


