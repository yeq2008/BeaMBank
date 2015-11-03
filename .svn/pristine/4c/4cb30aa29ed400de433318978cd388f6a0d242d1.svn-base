function setValue(obj){
	if(undefined == obj || null == obj)
		return "";
	return obj;
}

//客户基本信息
function loadCustInfoData(obj){
	var info = obj.evalJSON();
	document.getElementById('value_id1').innerHTML=setValue(info.IDEN_NUM_TYPE);//证件类型
	document.getElementById('value_id2').innerHTML=setValue(info.GOVE_IDEN_NUM);//证件号码
	document.getElementById('value_id3').innerHTML=setValue(info.CH_NAME);//客户名称
	document.getElementById('value_id4').innerHTML=setValue(info.GENDER);//性别
	document.getElementById('value_id5').innerHTML=setValue(info.BIRTHDATE);//出生日期
	document.getElementById('value_id6').innerHTML=setValue(info.RACE);//民族
	document.getElementById('value_id7').innerHTML=setValue(info.OCCUPATION);//职业
	document.getElementById('value_id8').innerHTML=setValue(info.EDUCATION_CODE);//教育水平
	document.getElementById('value_id9').innerHTML=setValue(info.MARITAL_STATUS);//婚姻状态
	document.getElementById('value_id10').innerHTML=setValue(info.ANN_INC);//年收入
	document.getElementById('value_id11').innerHTML=setValue(info.NATIONALITY);//国籍全称
	document.getElementById('value_id12').innerHTML=setValue(info.EMPLOYMENT_TYPE);//雇佣类型
	document.getElementById('value_id13').innerHTML=setValue(info.POSITION);//职称
	document.getElementById('value_id14').innerHTML=setValue(info.UNITPROPERTY);//单位性质
	document.getElementById('value_id15').innerHTML=setValue(info.POPULATION);//家庭人口数
	document.getElementById('value_id16').innerHTML=setValue(info.YEAROFSERVICE);//工作年限
	document.getElementById('value_id17').innerHTML=setValue(info.FREE_TRADE_AREA);//自贸区类型
	document.getElementById('value_id18').innerHTML=setValue(info.UNITCITY);//单位所在城市
	document.getElementById('value_id19').innerHTML=setValue(info.INDUSTRY_CODE_HK);//行业类型_香港标准
	document.getElementById('value_id23').innerHTML=setValue(info.INDUSTRY_CODE);//行业类型_中国标准
	document.getElementById('value_id20').innerHTML=setValue(info.ADDR_STREET1);//客户地址
	document.getElementById('value_id21').innerHTML=setValue(info.PHONE_NUMBER);//手机号码
	document.getElementById('value_id22').innerHTML=setValue(info.TEL_NUMBER);//座机号码
}

//核心账户
function loadCoreCustData(obj){
	var info = obj.evalJSON();
	document.getElementById('value_id1').value=setValue(info.QUART_CONTR_AMT);//当前贡献度 
	document.getElementById('value_id2').value=setValue(info.AUM_AVG);//当期AUM月日均
	document.getElementById('value_id3').value=setValue(info.AUM_EVN_GRADE);//AUM客户评级 
	document.getElementById('value_id4').value=setValue(info.QUART_CONTR_GRADE);//贡献度评级 
	document.getElementById('value_id5').value=setValue(info.CUST_MGR_NM);//主办客户经理 
}

//返回
function returnBack(){
	jsHelper.returnBack();
}