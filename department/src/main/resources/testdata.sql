INSERT INTO department (id,name,create_date,upper_department) VALUES
    (1001,'Департамент Самары', '1971-07-13',null);
INSERT INTO department (id, name,create_date,upper_department) VALUES
    (1002,'Департамент Образования', '1998-07-13',1001),
    (1003,'Департамент Городского Хозяйства', '1978-02-13',1002),
    (1004,'Департамент Транспорта Администрации', '1978-07-13',1003),
    (1005,'Департамент управления имущества','1978-07-13' ,1001),
    (1006,'Департамент благоустройства и экологии', '1978-07-14',1002),
    (1007,'Департамент финансов и экономического', '1978-07-13',1003),
    (1008,'Департамент Физической Культуры', '1978-07-13',1001);