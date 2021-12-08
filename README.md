## Product

## API 정리



### 🧾navbar

`GET` **/navbar/categoryList?depth1={categoryname}** : 카테고리 대분류로 목록 가져오기

`GET` **/navbar/brandList** : 브랜드 리스트 가져오기

-----------------------------------------

### 🧾productList

`GET` **/list/brand/{bno}?pageNo={no}** : 브랜드 상품리스트 가져오기 (pageNo)

`GET` **/list/category?depth1={depth1}&depth2={depth2}&depth3={depth3}** : 카테고리 상품리스트 가져오기 (pageNo)

`GET` **/list/addlike/{pid}** : 좋아요 상품 추가하기

`GET` **/list/delike/{pid}** : 좋아요 상품 취소하기

-----------------------------------------

### 🧾productDetail


`GET` **/product/detail/{pcid}** : 상품상세 불러오기

`GET` **/product/exit/{pcid}** : 상품상세 나가기

-----------------------------------------

### 👨‍👩‍👧HOME


`GET` **/bestproduct** : 베스트상품 불러오기 (10개)

`GET` **/brandnewproduct** : 신상품 불러오기 (10개)
