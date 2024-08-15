


API 명세서

기능                   Method           URL                         request         response            상태코드
일정 등록               POST          /api/schedule                 요청 body        등록 정보            200:정상등록
일정 조회               GET           /api/schedule/{scheduleId}    요청 param       단건 응답 정보        200:정상조회
일정 목록 조회           GET          /api/schedule                  요청 param       다건 응답 정보        200:정상조회
일정 수정               PUT           /api/schedule/{scheduleId}    요청 body         수정 정보            200:정상수정
일정 삭제               DELETE        /api/schedule/{scheduleId}    요청 param                            200:정상삭제



ERD
Schedule
1.scheduleId
2.content
3.memberName
4.password
5.createAt
6.modifiedAt



# scheduleManagement
# scheduleManagement
# scheduleManagement
