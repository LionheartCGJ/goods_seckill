--数据库初始化脚本
mysql -uCGJ -p123456;

--创建数据库
CREATE DATABASE seckill;

--使用数据库
use seckill;

--创建数据库表
create table seckill(
`seckill_id` bigint not null auto_increment comment '商品ID',
`name` varchar(120) not null comment '商品名称',
`number` int not null comment '商品数量',
`start_time` timestamp not null comment '开始时间',
`end_time` timestamp not null comment '结束时间',
`create_time` timestamp not null default current_timestamp comment '创建时间',
primary key (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB auto_increment=1000 default charset=utf8 comment '商品库存表';

--初始化数据--
insert into
    seckill(name,number,start_time,end_time)
values
    ('1000元秒杀iphone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
    ('500元秒杀ipad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
    ('300元秒杀小米4',300,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
    ('200元秒杀红米note',400,'2015-11-01 00:00:00','2015-11-02 00:00:00');

--秒杀成功明细表--
--用户登录认真相关的信息--
create table success_killed(
`seckill_id` bigint not null comment '秒杀商品ID',
`user_phone` bigint not null comment '用户手机号',
`state` tinyint not null default -1 comment '状态标识：-1 无效，0 成功，1 已付款',
`create_time` timestamp not null comment '创建时间',
primary key(seckill_id,user_phone),/*联合主键*/
key idx_create_time(create_time)
)engine=InnoDB default charset=utf8 comment '秒杀成功明细表';
