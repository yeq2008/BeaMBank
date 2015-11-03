/**
 * 催收详情
 */
var isaddlxfs = false;//判断是否需要更改联系方式
var CollectionNo;//催收id
var SerialNo;//借据编号
function init(){
	webviewutil.onloadData();
	document.getElementById('xzkhcl_date').value = dateFormat(new Date(),"yyyy/MM/dd");
	document.getElementById('xzcsxx_date').value = dateFormat(new Date(),"yyyy/MM/dd");
	initTitle();
}

function initTitle(){
	webviewutil.getParams('gglxfs_title','TitleCNCYB');
}

function sendParams(id,obj){
	var jsonArray = obj.evalJSON();
	var length = jsonArray.length;
	var div = document.getElementById("div_"+id);
	var classname = "radioselected";
	if(length > 0){
		for(var i = 0; i < length; i++){
			var o = jsonArray[i];
			var input = document.createElement("input");
			input.id = "div_"+id+"_"+i;
			input.type = "button";
			if(i == 0){
				input.className = "radioselected";
			}else{
				input.className = "radionoselected";
			}
			input.value = o.NAME;
			input.setAttribute("onclick","selectChange('"+id+"','"+o.NO+"','"+i+"','"+length+"')");
			div.appendChild(input);
		}
	}
}

function selectChange(id,code,index,length){
	var obj;
	var classname;
	for(var i=0;i<length;i++){
		obj = document.getElementById("div_"+id+"_"+i);
		if(index == i){
			classname = obj.className;
			if("radionoselected" == classname){
				obj.className = "radioselected";
				document.getElementById(id).value = code;
			}
		}else{
			obj.className = "radionoselected";
		}
	}
}

function setValue(obj){
	if(undefined == obj || null == obj)
		return "";
	return obj;
}

function sendSelect(inputid,valueid,code,value){
	if('xzcsxx_result' == inputid && '1' == code){
		document.getElementById('model5_1_1').style.visibility = "visible";
	}else{
		document.getElementById('model5_1_1').style.visibility = "hidden";
	}
	document.getElementById(inputid).value = code;
	document.getElementById(valueid).value = value;
}

function dateFormat(date,format){
	var o = {        
	        "M+" : date.getMonth()+1,
	        "d+" : date.getDate(),
	        "h+" : date.getHours(),
	        "m+" : date.getMinutes(),
	        "s+" : date.getSeconds(),
	        "q+" : Math.floor((date.getMonth()+3)/3),
	        "S" : date.getMilliseconds()
	        };        
	        if(/(y+)/.test(format)) 
	        	format=format.replace(RegExp.$1,(date.getFullYear()+"").substr(4 - RegExp.$1.length));        
	        for(var k in o)
	        	if(new RegExp("("+ k +")").test(format))        
	        		format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));        
	        return format;  
}

/*
 * 新增记录
 */
function getData(){
	//客户承诺记录
	var ProsimeAmt = getValueById('xzkhcl_money');
	var ProsimeDate = getValueById('xzkhcl_date');
		
	//历史催收记录
	var CollectionQueue = 'PhoneL1';
	var CollectionTime = getValueById('xzcsxx_date');
	var CollectionObject = getValueById('xzcsxx_people');
	var csPhoneNo = getValueById('xzcsxx_phone');
	
	var CollectionResult = getValueById('xzcsxx_result');
	var CollectionType = '01';//催收方式
	var Remark = getValueById('xzcsxx_remark');
	
	//更改联系方式
	var title = getValueById('gglxfs_title');
	var FullName = getValueById('gglxfs_name');
	var Relationship = getValueById('gglxfs_re');
	var PhoneNo = getValueById('gglxfs_telephone');
	var MobileNo = getValueById('gglxfs_mobilephone');
	var Address = getValueById('gglxfs_addr');
	if(null == ProsimeAmt || '' == ProsimeAmt){
		if('1' == CollectionResult){
			alert("承诺还款金额不能为空");
			return;
		}
	}
	
	if(null == csPhoneNo || '' == csPhoneNo){
		alert("催收电话不能为空");
		return;
	}
	
	if(null == Remark || '' == Remark){
		alert("催收记录备注不能为空");
		return;
	}
	
	if(isaddlxfs){
		if(null == PhoneNo || '' == PhoneNo){
			alert("固定电话不能为空");
			return;
		}
		if(null == FullName || '' == FullName){
			alert("联系人名称不能为空");
			return;
		}
		if(null == Address || '' == Address){
			alert("联系地址不能为空");
			return;
		}
		if(null == MobileNo || '' == MobileNo){
			alert("移动电话不能为空");
			return;
		}	
	}
	var json = '{"CustormerPromise":[{"LoanNo":"'+SerialNo+'","ProsimeAmt":"'+ProsimeAmt+'","ProsimeDate":"'+ProsimeDate+'"}],'
	+'"collectionHis":[{"CollectionNo":"'+CollectionNo+'","CollectionQueue":"'+CollectionQueue+'","CollectionTime":"'+CollectionTime+'",'
			 +'"CollectionObject":"'+CollectionObject+'","PhoneNo":"'+csPhoneNo+'",'
			 +'"CollectionResult":"'+CollectionResult+'","CollectionType":"'+CollectionType+'",'
			 +'"Remark":"'+Remark+'"}]';
	if(isaddlxfs){
		json+=',"UPTEL":[{"LoanNo":"'+SerialNo+'","Title":"'+title+'","FullName":"'+FullName+'",'
			 +'"Relationship":"'+Relationship+'","PhoneNo":"'+PhoneNo+'",'
			 +'"MobileNo":"'+MobileNo+'","Address":"'+Address+'"}]';
	}
	json+='}';
	webviewutil.handData(json);
}

function getValueById(id){
	return document.getElementById(id).value;
}

function loadData(obj){
	if(undefined == obj || '' == obj)
		return;
	var info = obj.evalJSON();
	
	CollectionNo = info.SerialNoCI;
	SerialNo = setValue(info.SerialNo);
	
	document.getElementById('m1_test1').innerHTML =  SerialNo;//借据编号              
	document.getElementById('m1_test2').innerHTML =  setValue(info.CustomerName);//客户名称          
	document.getElementById('m1_test3').innerHTML =  setValue(info.Title);//称谓                     
	document.getElementById('m1_test4').innerHTML =  setValue(info.BusinessType);//业务品种          
	document.getElementById('m1_test5').innerHTML =  setValue(info.Currency);//贷款币种              
	document.getElementById('m1_test6').innerHTML =  setValue(info.BusinessSum);//贷款金额           
	document.getElementById('m1_test7').innerHTML =  setValue(info.NormalBalance);//正常本金余额     
	document.getElementById('m1_test8').innerHTML =  setValue(info.LoanTerm);//贷款期限(期数)        
	document.getElementById('m1_test9').innerHTML =  setValue(info.PutOutDate);//放款日期            
	document.getElementById('m1_test10').innerHTML = setValue(info.MaturityDate);//贷款到期日        
	document.getElementById('m1_test11').innerHTML = setValue(info.AcctAmount);//账户管理费          
	document.getElementById('m1_test12').innerHTML = setValue(info.OverDueBalance);//期供欠本        
	document.getElementById('m1_test13').innerHTML = setValue(info.InterestBalance);//期供欠息       
	document.getElementById('m1_test14').innerHTML = setValue(info.InterestBalance2);//应还逾期利息  
	document.getElementById('m1_test15').innerHTML = setValue(info.OdinteBalance);//应还外币加收罚息 
	document.getElementById('m1_test16').innerHTML = setValue(info.AllBalance);//当期应还金额        
	document.getElementById('m1_test17').innerHTML = setValue(info.ClassifyResultName);//五级分类结果
	document.getElementById('m1_test18').innerHTML = setValue(info.LoanRate);//执行年利率            
	document.getElementById('m1_test19').innerHTML = setValue(info.DefaultDueDay);//默认还款日       
	document.getElementById('m1_test20').innerHTML = setValue(info.NextDueDate);//下次还款日         
	document.getElementById('m1_test21').innerHTML = setValue(info.ReScheduleFlag);//是否为重组贷款  
	document.getElementById('m1_test22').innerHTML = setValue(info.FirstODDate);//首次逾期日期
	document.getElementById('m1_test23').innerHTML = setValue(info.TATimes);//累计逾期期数
	document.getElementById('m1_test24').innerHTML = setValue(info.LCATimes);//连续逾期期数          
	document.getElementById('m1_test25').innerHTML = setValue(info.OverDueDays);//当前逾期天数       
	document.getElementById('m1_test26').innerHTML = setValue(info.LastSum);//贷款本金余额           
	document.getElementById('m1_test27').innerHTML = setValue(info.CustomerID);//客户ID              
	document.getElementById('m1_test28').innerHTML = setValue(info.SerialNoCI);//催收ID
	
	if(undefined != info.payback3){
		createTbody(info.payback3);//最近三笔还款信息
	}else{
		document.getElementById('model2_1').style.display = "none";
	}
//	if(undefined != info.CustormerPromise){
//		document.getElementById('model4_3').style.visibility = "visible";
//		custCNRecord(info.CustormerPromise);//客户承诺记录
//	}
	if(undefined != info.collectionHis){
		cuishouRecord(info.collectionHis);//催收历史记录
		document.getElementById('model_2_1').style.visibility = "visible";
	}else{
		document.getElementById("ifadd").style.height = 300+'px';
	}
	
	if(undefined != info.IndTel){
		jkrlxinfo(info.IndTel);//借款人联系信息
	}else{
		document.getElementById('model3_2').style.display = "none";
	}
}

//最近三笔还款信息
function createTbody(jsonArray){
	if(undefined == jsonArray)
		return;
	var length = jsonArray.length;
	if(length > 0){
		var table;
		var tr1,tr2,tr3,tr4;
		var obj;
		var div = document.getElementById('last3tbody');
		for(var i = 0; i < jsonArray.length; i++){
			obj = jsonArray[i];
			if('' != obj){
				table = document.createElement("table");
				if(0 == i){
					table.className = "tablepadding";
				}else{
					table.className = "table";
				}
				
				tr1 = document.createElement("tr");
				var th1 = document.createElement("td");
				th1.colSpan = 2;
				th1.align = "left";
				th1.innerHTML = '<input type="text" readonly="readonly" class="inputstyle1" value="'+setValue(obj.LOANSERIALNO)+'"/>';
				tr1.appendChild(th1);
				var th2 = document.createElement("td");
				th2.colSpan = 2;
				th2.align = "right";
				th2.innerHTML = '<input type="text" readonly="readonly" class="inputstyle2" value="'+setValue(obj.PAYDATE)+'"/>';
				tr1.appendChild(th2);
				table.appendChild(tr1);
				
				tr2 = createTr("实还日期","实还总金额",obj.ACTUALPAYDATE,obj.AllActualAmount);
				table.appendChild(tr2);
				
				tr3 = createTr("实还本金","实还利息",obj.ACTUALPAYCORPUSAMT,obj.ACTUALPAYINTEAMT);
				table.appendChild(tr3);
				
				tr4 = createTr("实还逾期罚息","实还逾期复息",obj.ACTUALFINEAMT,obj.ACTUALCOMPDINTEAMT);
				table.appendChild(tr4);
				
				div.appendChild(table);
				
				if(length > i+1){
					var hr = document.createElement("hr");
					hr.className = "hr";
					div.appendChild(hr);
				}
			}
		}
	}
}

//客户承诺记录
function custCNRecord(jsonArray){
	if(undefined == jsonArray)
		return;
	var length = jsonArray.length;
	if(length > 0){
		var table;
		var tr1,tr2,tr3,tr4;
		var obj;
		var div = document.getElementById('custCLRecord');
		for(var i = 0; i < jsonArray.length; i++){
			obj = jsonArray[i];
			if('' != obj){
				table = document.createElement("table");
				if(0 == i){
					table.className = "tablepadding";
				}else{
					table.className = "table";
				}
				
				tr1 = document.createElement("tr");
				var th1 = document.createElement("td");
				th1.colSpan = 2;
				th1.align = "left";
				th1.innerHTML = '<input type="text" readonly="readonly" class="inputstyle1" value="'+setValue(obj.SerialNo)+'"/>';
				tr1.appendChild(th1);
				var th2 = document.createElement("td");
				th2.colSpan = 2;
				th2.align = "right";
				th2.innerHTML = '<input type="text" readonly="readonly" class="inputstyle2" value="'+setValue(obj.ProsimeDate)+'"/>';
				tr1.appendChild(th2);
				table.appendChild(tr1);
				
//				tr1 = createTr("承诺流水编号","承诺还款金额",obj.SerialNo,obj.ProsimeAmt);
//				table.appendChild(tr1);
				
//				tr2 = createTr("承诺还款日期","录入人",obj.ProsimeDate,obj.InputUser);
//				table.appendChild(tr2);
				
				tr2 = createTr("承诺还款金额","录入人",obj.ProsimeAmt,obj.InputUser);
				table.appendChild(tr2);
				
				tr3 = createTr("录入机构","录入日期",obj.InputOrg,obj.InputDate);
				table.appendChild(tr3);
				
				tr4 = createTr("更新人","",obj.UpdateUser,"");
				table.appendChild(tr4);
				
				div.appendChild(table);
				
				if(length > i+1){
					var hr = document.createElement("hr");
					hr.className = "hr";
					div.appendChild(hr);
				}
			}
		}
	}
}

//催收历史记录
function cuishouRecord(jsonArray){
	if(undefined == jsonArray)
		return;
	var length = jsonArray.length;
	if(length > 0){
		var table;
		var tr1,tr2,tr3,tr4,tr5,tr6,tr7;
		var obj;
		var div = document.getElementById('cuishourecord');
		for(var i = 0; i < jsonArray.length; i++){
			obj = jsonArray[i];
			if('' != obj){
				table = document.createElement("table");
				if(0 == i){
					table.className = "tablepadding";
				}else{
					table.className = "table";
				}
				
				tr1 = document.createElement("tr");
				var th1 = document.createElement("td");
				th1.colSpan = 2;
				th1.align = "left";
				th1.innerHTML = '<input type="text" readonly="readonly" class="inputstyle1" value="'+setValue(obj.SerialNo)+'"/>';
				tr1.appendChild(th1);
				var th2 = document.createElement("td");
				th2.colSpan = 2;
				th2.align = "right";
				th2.innerHTML = '<input type="text" readonly="readonly" class="inputstyle2" value="'+setValue(obj.CollectionTime)+'"/>';
				tr1.appendChild(th2);
				table.appendChild(tr1);
				
//				tr1 = createTr("催收流水号","任务队列",obj.SerialNo,obj.CollectionQueue);
//				table.appendChild(tr1);
				
//				tr2 = createTr("任务生成时间","催收时间",obj.CreateDate,obj.CollectionTime);
//				table.appendChild(tr2);
				
				tr2 = createTr("任务队列","任务生成时间",obj.CollectionQueue,obj.CreateDate);
				table.appendChild(tr2);
				
				tr3 = createTr("催收对象","催收电话",obj.CollectionObject,obj.PhoneNo);
				table.appendChild(tr3);
				
				tr4 = createTr("催收方式","催收执行人",obj.CollectionType,obj.ExecutorName);
				table.appendChild(tr4);
				
				tr5 = createTrSingle("催收执行机构",obj.ExecuteOrgName);
				table.appendChild(tr5);
				
				tr6 = createTrSingle("催收执行结果",obj.CollectionResultName);
				table.appendChild(tr6);
				
				tr7 = createTrSingle("催收记录备注",obj.Remark);
				table.appendChild(tr7);
				
				div.appendChild(table);
				if(length > i+1){
					var hr = document.createElement("hr");
					hr.className = "hr";
					div.appendChild(hr);
				}
			}
		}
	}
}

//借款人联系信息
function jkrlxinfo(jsonArray){
	if(undefined == jsonArray)
		return;
	var length = jsonArray.length;
	if(length > 0){
		var table;
		var tr1,tr2,tr3,tr4;
		var obj;
		var div = document.getElementById('jkrlxinfo');
		for(var i = 0; i < jsonArray.length; i++){
			obj = jsonArray[i];
			if('' != obj){
				table = document.createElement("table");
				if(0 == i){
					table.className = "tablepadding";
				}else{
					table.className = "table";
				}
				
				tr1 = createTr("电话类型","国际区号",obj.TelType,obj.IntArea);
				table.appendChild(tr1);
				
				tr2 = createTr("区号","电话号码",obj.Area,obj.Telephone);
				table.appendChild(tr2);
				
				tr3 = createTr("分机号","客户是否接受短息",obj.Ext,obj.IsInformation);
				table.appendChild(tr3);
				
				tr4 = createTr("是否最新","",obj.IsNew,"");
				table.appendChild(tr4);
				
				div.appendChild(table);
				if(length > i+1){
					var hr = document.createElement("hr");
					hr.className = "hr";
					div.appendChild(hr);
				}
			}
		}
	}
}

function createTrSingle(title,content){
	if(undefined == content || null == content)
		content = "";
	var tr;
	var td1,td2;
	tr = document.createElement("tr");
	
	td1 = document.createElement("td");
	td1.style.width = "20%";
//	td1.innerHTML = '<input type="text" readonly="readonly" class="inputtext" value="'+title+'"/>';
//	td1.innerHTML = '<span class="inputtext">'+title+'<span>';
//	td1.innerHTML = '<textarea rows="1" cols="3" class="inputtext">'+title+'</textarea>';
	td1.innerHTML = '<label class="labeltext">'+title+'</label>';
	tr.appendChild(td1);
	
	td2 = document.createElement("td");
	td2.style.width = "80%";
	td2.colSpan = 3;
//	td2.innerHTML = '<input type="text" readonly="readonly" class="inputvalue" value="'+content+'"/>';
//	td2.innerHTML = '<span class="inputvalue">'+content+'<span>';
//	td2.innerHTML = '<textarea rows="1" cols="3" class="inputvalue">'+content+'</textarea>';
	td2.innerHTML = '<label class="labelvalue">'+content+'</label>';
	tr.appendChild(td2);
	
	return tr;
}

function createTr(title1,title2,content1,content2){
	if(undefined == content1 || null == content1)
		content1 = "";
	if(undefined == content2 || null == content2)
		content2 = "";
	var tr;
	var th1,th2;
	var td1,td2;
	tr = document.createElement("tr");
	
	th1 = document.createElement("td");
	th1.style.width = "20%";
	th1.innerHTML = '<input type="text" readonly="readonly" class="inputtext" value="'+title1+'"/>';
	tr.appendChild(th1);
	
	td1 = document.createElement("td");
	td1.style.width = "30%";
	td1.innerHTML = '<input type="text" readonly="readonly" class="inputvalue" value="'+content1+'"/>';
	tr.appendChild(td1);
	
	if("" == title2)
		return tr;
	th2 = document.createElement("td");
	th2.style.width = "20%";
	th2.innerHTML = '<input type="text" readonly="readonly" class="inputtext" value="'+title2+'"/>';
	tr.appendChild(th2);
	
	td2 = document.createElement("td");
	td2.style.width = "30%";
	td2.innerHTML = '<input type="text" readonly="readonly" class="inputvalue" value="'+content2+'"/>';
	tr.appendChild(td2);
	
	return tr;
}

function changeLxfs(id){
	var obj = document.getElementById(id);
	var display = obj.style.display;
	if(display == 'block'){
		obj.style.display = 'none';
		isaddlxfs = false;
	}else{
		obj.style.display = 'block';
		isaddlxfs = true;
	}
}

function sendDate(date,id){
	var s = date.replace("年","/").replace("月","/").replace("日","");
	document.getElementById(id).value = s;
}

function redirectAddr(id){
	document.getElementById(id).scrollIntoView();
}