SmartStruts��Ŀ˵����

����ĿΪѧϰ��struts1֮����ѧϰ��java�߼�֪ʶ֮�����Զ�д

��ʵ�˿��������struts1ʵ������һ������servlet����web.xml����
�����е�http���󽻸��������servlet���д�������һ�����������ʱ��
����web.xml���е����ý����󽻸�servlet��tomcat�������𴴽����servlet
ʵ����servlet����servlet��init�����������servlet��init�������桢���Ƕ�ȡ��smart-struts.xml�������ļ�
���ж�ȡsmart-struts.xml�����ļ���ʱ��ʹ�� commons-digester-1.8���������������
������������rule.xml�еĹ涨�����û����õ�xml�ļ������û����õ�xml�ļ�
����������������dom�����װ��MoudleConfig���󹤳����С������xml�������൱��
һ��������������������ŵ���MoudleConfig���󡢽�xml���õ��е�action�����һ��ActionConifg
�������MoudleConfig�����С�����xml���õ��е�formbean����һ��formBeanConfig����
���ζ������action���С�ʵ���Ͼ��ǽ�xml��dom���������������Ա��ֳ�����

��һ����������ʱ�򡢽�����ܽ��д�����ܽ����������������path������moudleConfig
���ҵ���Ӧ��action������action�����ҵ���Ӧformbeanʵ����formbean������ʹ�õ�
����Class.formName���������������װ��formbean����
��Ϊ�ύ�����������������string���͵Ķ�ʵ�����Ͳ�һ������string���������������ʹ����һ������Ϊ
commons-beanutils-1.8.0.jar�����������������ɽ����������װ��formbean����
������͵�ת����Ȼ�����action��type�����ҵ���Ӧ��action�������÷������ʵ����
��action�д���ղ�ʵ������formbean�Լ�request��response���󡢽����Ǵ��ݸ�action��execute
������action����󷵻�һ��forword���󡢴�ʱ�ӵ�ǰ��actionConfig�ҵ���Ӧ��forword
�������forword������ѡ����ת����ҳ��