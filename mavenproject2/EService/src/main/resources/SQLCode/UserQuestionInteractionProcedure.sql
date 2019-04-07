use mysqlProd;
DELIMITER //
drop procedure if exists UserQuestionInteractionProcedure//
create procedure UserQuestionInteractionProcedure(in userNameParam varchar(40), in noofQuestions int,in questionType varchar(40))
deterministic
begin
	#first check if UserQuestionInteractionProcedure contains noofQuestoins entries for
        #the given user
        #Lets Write down the strategy
        #) 50 % Tough Question
        #) 20% medium Question
        #) 20% Easy Question
        #) 10% new Questions
        DECLARE questionTypeEnum int;
        DECLARE userIdVar int;
        DECLARE toughestCount int;
        DECLARE tougherCount int;
        DECLARE toughCount int;
        DECLARE normalCount int;
        DECLARE easyCount int;
        DECLARE easierCount int;
        DECLARE easiestCount int;
        DECLARE newCount int;
        DECLARE atoughCount int;
        DECLARE atougherCount int;
        DECLARE atoughestCount int;
        DECLARE anormalCount int;
        DECLARE aeasyCount int;
        DECLARE aeasierCount int;
        DECLARE aeasiestCount int;
        DECLARE anewCount int;
        DECLARE interactionSelectedCount int;
        DECLARE RAMDOM text default 'RANDOM';
        DECLARE WORDMEANING text default 'WORD_MEANING';
        DECLARE PTR text default 'POINT_TO_REMEMBER';
        select id into  userIdVar from EncephUser where UserName =userNameParam;
        if questionType like RAMDOM then
			set questionTypeEnum=-1;
        elseif questionType=WORDMEANING then
			set questionTypeEnum=1;
        elseif questionType=PTR then
			set questionTypeEnum=0;
		end if;
        
        create temporary table selectedIds(id int);
        if noofQuestions >0 then
        
			SET toughestCount=FLOOR((noofQuestions*17.5)/100);
			SET tougherCount=FLOOR((noofQuestions*11.6)/100);
			SET toughCount=FLOOR((noofQuestions*6)/100);
			SET normalCount=FLOOR((noofQuestions*25)/100);
			SET easyCount=FLOOR((noofQuestions*12.5)/100);
			SET easierCount=FLOOR((noofQuestions*8.3)/100);
			SET easiestCount=FLOOR((noofQuestions*4.2)/100);
			SET newCount=noofQuestions-(toughestCount+tougherCount+toughCount+normalCount+easyCount+easierCount+easiestCount);
			#select userIdVar,toughestCount,tougherCount,toughCount,normalCount,easyCount,easierCount,easiestCount,newCount ;
			#now we have to count if these number of questions are present in specified catagory
            select count(1) into atoughestCount from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and userId =userIdVar and DifficultyCatagory='Hardest';
			select count(1) into atougherCount  from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and UserId =userIdVar and DifficultyCatagory='Harder';
			select count(1) into atoughCount    from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and UserId =userIdVar and DifficultyCatagory='Hard';
			select count(1) into anormalCount   from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and UserId =userIdVar and DifficultyCatagory='Normal';
			select count(1) into aeasyCount     from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and UserId =userIdVar and DifficultyCatagory='Easy';
			select count(1) into aeasierCount   from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and UserId =userIdVar and DifficultyCatagory='Easier';
			select count(1) into aeasiestCount  from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and UserId =userIdVar and DifficultyCatagory='Easiest';
			#select userIdVar,atoughestCount,atougherCount,atoughCount,anormalCount,aeasyCount,aeasierCount,aeasiestCount,anewCount ;
            
            if toughestCount > atoughestCount then
            set tougherCount=tougherCount+toughestCount-atoughestCount;
            else
            set atoughestCount=toughestCount;
            end if;
            
            if tougherCount > atougherCount then
            set toughCount=toughCount+tougherCount-atougherCount;
            else 
            set atougherCount=tougherCount;
            end if;
            
            if toughCount > atoughCount then
            
            set normalCount=normalCount+toughCount-atoughCount;
            else 
            set atoughCount=toughCount;
            end if;
            
            if normalCount > anormalCount then
            
            set easyCount=easyCount+normalCount-anormalCount;
            else
				set anormalCount=normalCount;
            end if;
            
            if easyCount > aeasyCount then
            
            set easierCount=easierCount+easyCount-aeasyCount;
            else
				set aeasyCount=easyCount;
            end if;
            
            if easierCount > aeasierCount then
            
            set easiestCount=easiestCount+easierCount-aeasierCount;
            else
				set aeasierCount=easierCount;
            end if;
            
            if easiestCount < aeasiestCount then
            
				set aeasiestCount=easiestCount;
            end if;
            SET anewCount=noofQuestions-(atoughestCount+atougherCount+atoughCount+anormalCount+aeasyCount+aeasierCount+aeasiestCount);
			#select userIdVar,atoughestCount,atougherCount,atoughCount,anormalCount,aeasyCount,aeasierCount,aeasiestCount,anewCount ;
			
            
			insert into selectedIds(id) select questionId from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and userId =userIdVar and DifficultyCatagory='Hardest' order by LastAsked  limit atoughestCount ;
			insert into selectedIds(id) select questionId from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and userId =userIdVar and DifficultyCatagory='Harder' order by LastAsked limit atougherCount ;
			insert into selectedIds(id) select questionId from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and userId =userIdVar and DifficultyCatagory='Hard' order by LastAsked limit atoughCount;
			insert into selectedIds(id) select questionId from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and userId =userIdVar and DifficultyCatagory='Normal' order by LastAsked limit anormalCount;
			insert into selectedIds(id) select questionId from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and userId =userIdVar and DifficultyCatagory='Easy' order by LastAsked limit aeasyCount;
			insert into selectedIds(id) select questionId from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and userId =userIdVar and DifficultyCatagory='Easier' order by LastAsked limit aeasierCount;
			insert into selectedIds(id) select questionId from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and (questionTypeEnum=-1 or qm.type=questionTypeEnum)and userId =userIdVar and DifficultyCatagory='Easiest' order by LastAsked limit aeasiestCount;
            #select id from selectedIds;
            create temporary table myTemp(id int);
            select count(1) into interactionSelectedCount from selectedIds;
            if interactionSelectedCount < noofQuestions then 
				insert into myTemp select questionId as id from QuestionUserIntraction  where  userId = userIdVar;
                set anewCount=noofQuestions-interactionSelectedCount;
                insert into selectedIds(id) select q.id from Question q,QuestionMeta qm where q.meta_id=qm.id and (qm.personalQuestion = 0 or (qm.personalQuestion = 1 and qm.addedByUser=userNameParam)) and q.id not in (select id from myTemp) and  (questionTypeEnum=-1 or type=questionTypeEnum) limit anewCount;
                #select * from selectedIds;
					select count(1) into interactionSelectedCount from selectedIds;
                    if interactionSelectedCount < noofQuestions then
                    delete from myTemp;
                    insert into myTemp select id from selectedIds;
                    set anewCount=noofQuestions-interactionSelectedCount;
                    insert into selectedIds(id) select questionid from QuestionUserIntraction qui,Question qm where qui.QuestionId=qm.id and  (questionTypeEnum=-1 or qm.type=questionTypeEnum) and userId =userIdVar and questionid not in (select id from myTemp) order by LastAsked limit anewCount;
                    #select * from selectedIds;
                    end if;
            
            end if;
            drop table myTemp;
        end if;
        (select * from selectedIds );
        drop table selectedIds;
end;//
DELIMITER ;
call UserQuestionInteractionProcedure('prawal',30,'RANDOM')
