use mysql;
DELIMITER //
drop procedure if exists TopicMaintenanceProcedure//
create procedure TopicMaintenanceProcedure()
deterministic
begin
	DECLARE total_value INT;
	DECLARE starting_value INT;
	set starting_value=1;
	set total_value=50;
	create temporary table idst(id int);
	update Topic set nodeLevel=0 where parentTopic_id is null;
	label1: LOOP
		insert into idst (id) select id from  Topic where nodeLevel=(starting_value-1);     
		update Topic set nodeLevel=starting_value where parentTopic_id in (select id from idst); 
		delete from idst;
		IF starting_value < total_value THEN
			SET starting_value = starting_value + 1;
			ITERATE label1;
		END IF;
	LEAVE label1;
	END LOOP label1;
	select max(nodeLevel) into total_value from Topic;
    set starting_value=1;
	label2: LOOP
		update Topic a inner join Topic b on a.parentTopic_id=b.id set a.parentTagString = concat(ifnull(b.parentTagString,''), ' #',b.topicName) where a.nodeLevel=starting_value;
		IF starting_value < total_value THEN
			SET starting_value = starting_value + 1;
		ITERATE label2;
	END IF;
    LEAVE label2;
	END LOOP label2;
	drop table idst;
end;//
DELIMITER ;
call TopicMaintenanceProcedure();
