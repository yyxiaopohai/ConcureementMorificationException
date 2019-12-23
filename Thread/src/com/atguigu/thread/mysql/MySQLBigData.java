package com.atguigu.thread.mysql;

public class MySQLBigData {
    public static void main(String[] args) {

        //----------------------将$$修改为 ； ----存储过程-----------------------
       /* DELIMITER ;
        CALL insert_dept(100,10);
        CALL insert_emp(100001,500000);
        */


        //-----------------------创建存储过程----没有返回值-------------------------
        /*DELIMITER $$
        CREATE PROCEDURE insert_emp(IN START INT(10),IN max_num INT(10))
        BEGIN
        DECLARE i INT DEFAULT 0;
        #set autocommit =0 把autocommit设置成0
        SET autocommit = 0;
        REPEAT
            SET i = i + 1;
            INSERT INTO emp (empno, ename ,job ,mgr ,hiredate ,sal ,comm ,deptno ) VALUES ((START+i) ,rand_string(6),'SALESMAN',0001,CURDATE(),2000,400,rand_num());
            UNTIL i = max_num
        END REPEAT;
        COMMIT;
        END $$*/

        //--------------------创建函数--有返回值----------------------------
        //--------------------随机产生字符串--------------------------------
        /*DELIMITER $$
        CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
        BEGIN
        DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
        DECLARE return_str VARCHAR(255) DEFAULT '';
        DECLARE i INT DEFAULT 0;
        WHILE i < n DO
        SET return_str =CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1));
        SET i = i + 1;
        END WHILE;
        RETURN return_str;
        END $$

        #假如要删除
        #drop function rand_string;*/

        //--------------------------随机产生部门编号-------------------------
        /*#用于随机产生部门编号
        DELIMITER $$
        CREATE FUNCTION rand_num( )
        RETURNS INT(5)
        BEGIN
        DECLARE i INT DEFAULT 0;
        SET i = FLOOR(100+RAND()*10);
        RETURN i;
        END $$


        #假如要删除
        #drop function rand_num;*/


    }
}