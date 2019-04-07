use mysqlProd;
DELIMITER //
drop procedure if exists MaintenanceProcedure//
create procedure MaintenanceProcedure()
deterministic
begin
        update QuestionUserIntraction set DifficultyCatagory='Hardest' 	where (TimesCorrect/TimesAsked between 0.0 and 0.16)  ;
		update QuestionUserIntraction set DifficultyCatagory='Harder' 		where (TimesCorrect/TimesAsked between 0.16 and 0.30) ;
		update QuestionUserIntraction set DifficultyCatagory='Hard' 		where (TimesCorrect/TimesAsked between 0.30 and 0.44) ;
		update QuestionUserIntraction set DifficultyCatagory='Normal' 		where (TimesCorrect/TimesAsked between 0.44 and 0.58) ;
		update QuestionUserIntraction set DifficultyCatagory='Easy' 		where (TimesCorrect/TimesAsked between 0.58 and 0.72) ;
		update QuestionUserIntraction set DifficultyCatagory='Easier' 		where (TimesCorrect/TimesAsked between 0.72 and 0.86) ;
		update QuestionUserIntraction set DifficultyCatagory='Easiest' 		where (TimesCorrect/TimesAsked between 0.86 and 1) ;			
        select * from QuestionUserIntraction order by DifficultyCatagory;
end;//
DELIMITER ;
call MaintenanceProcedure();
