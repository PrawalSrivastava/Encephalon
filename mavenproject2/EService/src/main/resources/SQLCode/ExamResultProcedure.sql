use mysqlProd;
DELIMITER //
drop procedure if exists ExamResultProcedure//
create procedure ExamResultProcedure(in userName varchar(40), in SuccessIds varchar(max), in FailureIds varchar(max))
deterministic
begin
	#first check if UserQuestionInteractionProcedure contains noofQuestoins entries for
        #the given user
	select id from Question LIMIT noofQuestions ;
end;//
DELIMITER ;



CREATE DEFINER=`root`@`localhost` PROCEDURE `ExamResultProcedure`(in userName varchar(40), in SuccessIds varchar(6000), in FailureIds varchar(6000))\n    DETERMINISTIC\nbegin\n	
#first check if QuestionUserInteractionProcedure contains noofQuestoins entries for\n    
    #the given user\n   
     INSERT INTO QuestionUserIntraction (UserId,QuestionId,TimesAsked) \n     
   VALUES (1,1,1),(1,2,1) ON DUPLICATE KEY UPDATE TimesAsked=TimesAsked+1;\n  
      \nend', 'utf8', 'utf8_general_ci', 'latin1_swedish_ci'
