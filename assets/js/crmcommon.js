function setValue(obj){
	if(undefined == obj || null == obj)
		return "";
	return obj;
}

//�ͻ�������Ϣ
function loadCustInfoData(obj){
	var info = obj.evalJSON();
	document.getElementById('value_id1').innerHTML=setValue(info.IDEN_NUM_TYPE);//֤������
	document.getElementById('value_id2').innerHTML=setValue(info.GOVE_IDEN_NUM);//֤������
	document.getElementById('value_id3').innerHTML=setValue(info.CH_NAME);//�ͻ�����
	document.getElementById('value_id4').innerHTML=setValue(info.GENDER);//�Ա�
	document.getElementById('value_id5').innerHTML=setValue(info.BIRTHDATE);//��������
	document.getElementById('value_id6').innerHTML=setValue(info.RACE);//����
	document.getElementById('value_id7').innerHTML=setValue(info.OCCUPATION);//ְҵ
	document.getElementById('value_id8').innerHTML=setValue(info.EDUCATION_CODE);//����ˮƽ
	document.getElementById('value_id9').innerHTML=setValue(info.MARITAL_STATUS);//����״̬
	document.getElementById('value_id10').innerHTML=setValue(info.ANN_INC);//������
	document.getElementById('value_id11').innerHTML=setValue(info.NATIONALITY);//����ȫ��
	document.getElementById('value_id12').innerHTML=setValue(info.EMPLOYMENT_TYPE);//��Ӷ����
	document.getElementById('value_id13').innerHTML=setValue(info.POSITION);//ְ��
	document.getElementById('value_id14').innerHTML=setValue(info.UNITPROPERTY);//��λ����
	document.getElementById('value_id15').innerHTML=setValue(info.POPULATION);//��ͥ�˿���
	document.getElementById('value_id16').innerHTML=setValue(info.YEAROFSERVICE);//��������
	document.getElementById('value_id17').innerHTML=setValue(info.FREE_TRADE_AREA);//��ó������
	document.getElementById('value_id18').innerHTML=setValue(info.UNITCITY);//��λ���ڳ���
	document.getElementById('value_id19').innerHTML=setValue(info.INDUSTRY_CODE_HK);//��ҵ����_��۱�׼
	document.getElementById('value_id23').innerHTML=setValue(info.INDUSTRY_CODE);//��ҵ����_�й���׼
	document.getElementById('value_id20').innerHTML=setValue(info.ADDR_STREET1);//�ͻ���ַ
	document.getElementById('value_id21').innerHTML=setValue(info.PHONE_NUMBER);//�ֻ�����
	document.getElementById('value_id22').innerHTML=setValue(info.TEL_NUMBER);//��������
}

//�����˻�
function loadCoreCustData(obj){
	var info = obj.evalJSON();
	document.getElementById('value_id1').value=setValue(info.QUART_CONTR_AMT);//��ǰ���׶� 
	document.getElementById('value_id2').value=setValue(info.AUM_AVG);//����AUM���վ�
	document.getElementById('value_id3').value=setValue(info.AUM_EVN_GRADE);//AUM�ͻ����� 
	document.getElementById('value_id4').value=setValue(info.QUART_CONTR_GRADE);//���׶����� 
	document.getElementById('value_id5').value=setValue(info.CUST_MGR_NM);//����ͻ����� 
}

//����
function returnBack(){
	jsHelper.returnBack();
}