ʹ�ü�����
a��smartstrusts���  ���Ϳ�� �ӿ���������ٶ� �ȶ���
b��jQuery.validate.js
c��dbcp���ӳ�
d������ģʽ��ͬһ�����Ĵ���  ������⣺���󴴽�  �����˶���Ĵ������� ��װ�˶���Ĵ���  �������
���Ƴ���ṹ  MVCģʽ    jsp+javabean(С����)    
MVC�������Ŀ��
--------�����ܹ�---------
�ֲ㿪����
���ֲ�+������+���ݷ��ʲ�
ͨ�ò�
���ֲ�ʹ��jsp��jstl��jQuery����
���Ʋ�ʹ��smartstruts��ܵĿ�����
ҵ���ʹ��javabean
���ݷ��ʲ�ʹ��DAO+������dbcp���ӳ�
ͨ�ò㣺��ͬ��JavaBean  ��ͬ�Ĺ�����

--------Struts1-------- MVC��һ��˼��
1��Struts1��MVC
Struts1��һ��MVCʵ��
C���֣�ActionServlet�����RequestProcessor���
V���֣�JSP�������ǩ
M���֣�����JavaBean���  ActionForm\Action


2��Struts1��������
http://localhost:8080/Struts1/login.do
1���������URL�л�ȡ����������������.do֮��Ĳ���
2�����������ȡAction�����õ�name���Դ���ActionForm����
��������Я������Ϣ��װ�������������
��a������scope����ȥָ����Χ��Ѱ��ActionForm����Ѱ�ҵ�keyΪattribute����
��b������ҵ�ʹ������װ������Ϣ
  	���û���ҵ����´���һ��ActionForm����ʹ������װ����Ϣ
  	���Ҵ���scope��Χ
3������Action����name���ԡ�����ActionForm���� 	
common-beautif.jar 
4������Action���õ���type���Դ���Action���󡢲�ִ��execute����
����һ��ActionForword
5�����ݷ��ص�ActionForword�����ȡ��ͼ����ת����ҳ������ת
  a������forword�����е�redirect�����ж���ת��ʽ
  b���������Ϊtrue��ʹ��redirect
  c���������Ϊfalse��ʹ��forword��ʽ
  d��Ѱ��< forword>Ԫ��������Ϣʱ����ȥactionԪ��������ȥ�ҡ�����Ҳ�������ȥ<global-forword>Ԫ����ȥ��
  
  
  
  ===========smartStruts1=========
  1��������������Struts1����
  2����ͬ����һ�¼���ϸ��
  
  1��Action�����ҵ�񷽷������ı�
  2��Sturst-config.xml���������ı�
  ΪAction����һ��Method����
  
  
  ����common-digester-1.8.jar  ����xml