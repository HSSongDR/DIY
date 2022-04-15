# DIY team repo
Member : 곽민승, 노대영, 송형석, 이재희

# Groungd Rule
1. 모르는걸 부끄러워하지 않고 편하게 물어보기
2. 본인에게 주어진 역할은 미루지 말고 끝까지 해보기
3. 책임감 있게 리뷰하기
4. 리뷰는 리뷰일뿐, 감정 상하지 않기
5. 야근 지양, 정시 퇴근하기
6. 서로의 코딩 스타일을 인정하기.
7. 감정없이 이성적으로 리뷰하기.
8. 팀원끼리 하루 한번 인사하기
9. 셀프 리뷰 먼저하기

# Git 가이드

로컬에서 git repository를 관리할 새 폴더 생성

>  git clone https://github.com/HSSongDR/DIY.git

파일 수정 후 

> git status

를 하면 어떤 파일이 변했고 어떤 파일이 삭제, 생성되었는지 확인할 수 있음. 해당 파일 변화를 확인 후에 add 후 status확인해서 제대로 반영되었는지 다시 확인 하고 commit 진행

> git add .
>
> git commit -m "commit msg 자유롭게"

이후 해당 변경사항을 반영해야할 브랜치를 생성하여 해당 브랜치에 반영

> git branch development_이니셜



========================이부분은 참고

*(이미 branch가 지정되어있으면 그대로 진행

> git branch

를 통해 지정된 branch를 확인할 수 있고 

> git checkout [branch명]

으로 브랜치 변경 가능함)

===================================



원격 저장소 (github에 push)

> git push origin development_이니셜



이후 PR요청해서 approval이 되면 main branch에 변경점이 반영됩니다.

