create table Tag(
	tid int primary key auto_increment,
	tname varchar(100) not null,
	tcount int
)

create table favorite(
	fid int primary key auto_increment,
	flabel varchar(1000) not null,
	furl varchar(300) not null,
	ftags varchar(1000), 
	fdesc varchar(2000) 
)

drop table tag;
drop table favorite;
insert into tag(tname,tcount) values('Java',2);
insert into tag(tname,tcount) values('Struts',1);
insert into tag(tname,tcount) values('oracle',1);
insert into tag(tname,tcount) values('未分类',1);
insert into favorite(flabel,furl,ftags,fdesc) values('Apache Struts','http://www.apache.org/strtus/','Java,Strtus','Strtus官方站点');
insert into favorite(flabel,furl,ftags,fdesc) values('oracle','http://www.oracle.com','Java,oracle','oracle官方站点');

select * from tag;
select * from favorite;


alter table filminfo
      add constraint fk_typeid
          foreign key(typeid) references filmtype(typeid);


select * from filminfo;

create procedure findfilminfobytypename(tn varchar(100),out num int)
begin
	select count(*) into num
	from filminfo
	inner join filmtype
	on filmtype.typeid=filminfo.typeid
	where typename=tn;
end

select filmname,filmtype.typename,actor,director,ticketprice
  		from filminfo
  		inner join filmtype
  		on filminfo.typeid=filmtype.typeid
commit;