use mysqlProd;
DELIMITER //
drop procedure if exists UserChartProcedure//
create procedure UserChartProcedure(in userNameParam varchar(40), in chartType varchar(40))
deterministic
begin
		DECLARE PI text default 'PIE';
        DECLARE SCATTER text default 'SCATTER';
        DECLARE userIdVar int;
        DECLARE MaxTimesAsked int;
		DECLARE InteractionCount int;
		
		select id into  userIdVar from EncephUser where UserName =userNameParam;    
        if Upper(chartType) = PI then
			select count(1) into  InteractionCount from QuestionUserIntraction where userId =userIdVar;
			select DifficultyCatagory, ((count(1)*100)/InteractionCount) as Percentage from QuestionUserIntraction where userId =userIdVar group by DifficultyCatagory;
        elseif Upper(chartType) =SCATTER then
			select MAX(TimesAsked) into  MaxTimesAsked from QuestionUserIntraction where userId =userIdVar;
			select distinct  ((TimesAsked*100)/ MaxTimesAsked) as TimesAsked, ((TimesCorrect*100)/TimesAsked) as TimesCorrect from QuestionUserIntraction where userId =userIdVar;
        end if;
        
	
end;//
DELIMITER ;
call UserChartProcedure('prawal','scatter');
