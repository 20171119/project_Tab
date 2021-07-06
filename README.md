## 프로젝트명
```
잡동사니
```

### 프로젝트 설명
```
4개의 탭으로 구성된 앱

Tab1 - 연락처 연동 및 전화걸기
Tab2 - 갤러리 사진 연동 및 사진 찍기
Tab3 - 숫자 야구 게임
Tab4 - 날씨 API 이용한 날씨 앱 / 구글 맵 API 이용한 주변 음식점 찾기 앱
```

## 팀원
* 박종서
* 김영경



## 상세설명



### Tab1

<img src="https://user-images.githubusercontent.com/80809782/124596889-c1590300-de9d-11eb-81f8-d2dea1f41056.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/124597229-2c0a3e80-de9e-11eb-949c-a5cadeab21b5.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/124597367-5bb94680-de9e-11eb-9335-f996f58eda35.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/124597379-607dfa80-de9e-11eb-8bf2-3d4b2d173be4.png" width="200" height="400">

```
탭1은 로컬에 저장되어 있는 연락처 정보를 불러와 리스트뷰를 통해 보여준다.
연락처 이미지버튼을 누르면 연락처 정보를 불러와 리스트 뷰에 띄우고,
검색창을 통해 연락처를 찾을 수 있다.
보고싶은 연락처를 누르면 상세보기 창을 통해 상세보기 창으로 들어갈 수 있고, 
상세보기 창의 전화걸기 버튼을 누르면 그 번호가 입력된 전화앱으로 바로 연결되어 전화를 걸 수 있다. 
```



### Tab2

<img src="https://user-images.githubusercontent.com/80809782/124450791-ba5bc300-ddbf-11eb-948d-07c034e49558.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/124450892-d3fd0a80-ddbf-11eb-9186-396f7b9ab34d.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/124450919-db241880-ddbf-11eb-90ab-dfddc9246b73.png" width="200" height="400">

<img src="https://user-images.githubusercontent.com/80809782/124455544-abc3da80-ddc4-11eb-962e-4f6ec3f8deb7.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/124455307-643d4e80-ddc4-11eb-8a21-afa3da5c14af.png" width="200" height="400">

```
탭2의 갤러리 이미지버튼을 누르면 갤러리에서 선택한 사진을 탭2에 띄운다. 
카메라 버튼을 누르면 카메라 앱이 실행되어 사진을 찍을 수 있고, 그 사진은 로컬 저장되어 로컬에서 확인해 볼 수 있으며 갤러리 버튼을 통해 불러올 수 있다.
```



### Tab3

<img src="https://user-images.githubusercontent.com/80809782/124596795-a6868e80-de9d-11eb-907e-715c49510696.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/124596814-aab2ac00-de9d-11eb-9dca-c7f980eb9561.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/124453945-dc0a7980-ddc2-11eb-87cd-5231d7558c30.png" width="200" height="400"><img src="https://user-images.githubusercontent.com/80809782/124455675-cdbd5d00-ddc4-11eb-93f2-213c5366e048.png" width="200" height="400">

```
탭3에서는 숫자야구게임을 플레이할 수 있다.
help 버튼을 클릭하면 간단한 게임방법 설명을 볼 수 있고,
paly 버튼을 클릭하면 게임을 시작한다.
잘못된 입력을 했을때의 경고 메세지와, 입력한 숫자의 결과를 토스트메세지로 화면에 보여준다.
정답을 맞추면 다시할건지 그만할건지 물어보는 박스가 나오게 하였다.
```


### Tab4

<img src="https://user-images.githubusercontent.com/38155105/124453286-2f2ffc80-ddc2-11eb-928d-98e9d47c20e8.jpg" width="200" height="400"><img src="https://user-images.githubusercontent.com/38155105/124452957-d9f3eb00-ddc1-11eb-82cf-5dcac45f2629.jpg" width="200" height="400"><img src="https://user-images.githubusercontent.com/38155105/124452951-d95b5480-ddc1-11eb-87b6-0f743609f66d.jpg" width="200" height="400">

```
Tab4의 날씨 버튼을 클릭하면 날씨앱이 실행된다.
날씨앱에서는 검색한 도시의 여러 날씨 정보를 알려준다.
(OpenWeather API를 사용함)
```

<img src="https://user-images.githubusercontent.com/38155105/124453493-61d9f500-ddc2-11eb-9c9f-19299bffc4f4.jpg" width="200" height="400"><img src="https://user-images.githubusercontent.com/38155105/124453495-61d9f500-ddc2-11eb-95fb-da626cc9724b.jpg" width="200" height="400"><img src="https://user-images.githubusercontent.com/38155105/124453490-60a8c800-ddc2-11eb-9019-e2c555fb79c3.jpg" width="200" height="400">

```
Tab4의 지도 버튼을 클릭하면 지도앱이 실행된다.
처음 지도에 들어가면 현재 위치를 보여주는 지도를 보여준다.
현재 위치는 1초마다 갱신된다.
(Google Map API를 사용함)

Search Place 버튼과 Distance 버튼이 있는데,
Search Place 버튼을 누르면 Google Places API를 이용해서 주변 음식점들을 marker를 통해 보여주고,
주변 음식점 marker를 선택한 뒤 Distance 버튼을 누르면 
계속 갱신되는 현재 위치와 그 음식점 사이의 직선거리를 toast message로 보여준다.
```

## 실행 방법
```
 1. https://github.com/kelly1422/project_Tab 접속 
 2. app/release 폴더 이동 
 3. app-release.apk 다운로드 
 4. apk 를 통해 project_Tab 앱 설치 
```


