# struts2_Hibernate_orcale11g_intg

A couple note before going to the next step:


1. orcale11g doesn't include auto increase functionality. Use the following script:

--first create script:
create sequence <sequence_name>
minvalue 1
maxvalue 99999999
start with 1
increment by 1
nocycle
nocache
order;

--second create trigger:
create or replace trigger <trigger_name>
before insert on <table_name> for each row
begin
select <sequence_name>.nextval into :new.<column_name> from dual;
end <trigger_name>;


2. Remember to add the pack for ojdbc6.jar, otherwise will not be able to connect to orcale11g database


3. configeration details for sessionfactory
