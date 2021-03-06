/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ตรวจสอบข้อมูลล็อคอิน
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
checkLoginDup {
	SELECT COUNT(1) AS TOT
	FROM USER
	WHERE LOGIN_NAME = %s
	AND PASSWORD = %s	
}

loginUser {
	SELECT * 
	FROM USER
	WHERE LOGIN_NAME = %s
	AND PASSWORD = %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : นับจำนวนข้อมูล
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchCount {
		SELECT COUNT(1) AS TOT
	FROM USER
	WHERE 1=1
	AND LOGIN_NAME = %s
	AND PASSWORD = %s	
}
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : เก็บข้อมูลตรวจสอบสิทธิ user admin
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchAdmin {
	SELECT USER_ID
	, LOGIN_NAME
	, PASSWORD
	, CONCAT(FIRST_NAME , '    ' , LAST_NAME) AS FULLNAME
	, ADMIN
	FROM USER
	WHERE 1=1
	AND LOGIN_NAME = %s
	AND PASSWORD = %s	
}