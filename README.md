# NewsAppMVVM

기능
=========
뉴스 리스트 가져오기, 검색하기, 스크랩하기, 스크랩 뉴스 삭제

사용 기술
===========
databinding
navigation
coroutines
viewModel
liveData
room

라이브러리 버전
===============
kotlin version :  1.9.20
room : 2.5.0

이슈
=============
코틀린 1.9.20 에서 room 2.5.0 하위 버전 , kpt 사용시 계속해서 DAO 관련 오류 발생

해결방법 
room 2.5.0으로 올리고 kpt -> ksp로 migration


