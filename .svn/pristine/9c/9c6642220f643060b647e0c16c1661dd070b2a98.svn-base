function setValue(obj){
	if(undefined == obj || null == obj)
		return "";
	return obj;
}

//存款
function loadCunkuanData(obj){
	var jsonArray = obj.evalJSON();
	var length = jsonArray.length;
	if(length > 0){
		var tbody = document.getElementById("tbody");
		for(var i = 0; i < jsonArray.length; i++){
			var o = jsonArray[i];
			var tr = document.createElement("tr");
			tr.innerHTML = 
				  '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.CURR_CD)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.BALANCE)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.PROD_NM)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.EFFECT_RATE)+'"/> </td>'
				+ '<td style="width: 15%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.ACCT_STAUTS)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.MONTH_AVG_RMB)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.YEAR_AVG_RMB)+'"/> </td>';
			tbody.appendChild(tr);
		}
	}
}

//理财
function loadLicaiData(obj){
	var jsonArray = obj.evalJSON();
	var length = jsonArray.length;
	if(length > 0){
		var tbody = document.getElementById("tbody");
		for(var i = 0; i < jsonArray.length; i++){
			var o = jsonArray[i];
			var tr = document.createElement("tr");
			tr.innerHTML = 
				  '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.OPEN_DATE)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.PROD_NM)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.TRANS_AMT)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.TOT_VOL)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.FACE_VALUE)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.BALANCE)+'"/> </td>';
			tbody.appendChild(tr);
		}
	}
}

//保险
function loadBaoxianData(obj){
	var jsonArray = obj.evalJSON();
	var length = jsonArray.length;
	if(length > 0){
		var tbody = document.getElementById("tbody");
		for(var i = 0; i < jsonArray.length; i++){
			var o = jsonArray[i];
			var tr = document.createElement("tr");
			tr.innerHTML = 
				  '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.PROD_NM)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.CURR_CD)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.INSSRANCE_AMT)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.PAY_TERM)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.INSS_MATUR_DT)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.BALANCE_RMB)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.AUM_BAL)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.ACCT_STAT_CD)+'"/> </td>';
			tbody.appendChild(tr);
		}
	}
}

//贷款
function loadDaikuanData(obj){
	var jsonArray = obj.evalJSON();
	var length = jsonArray.length;
	if(length > 0){
		var tbody = document.getElementById("tbody");
		for(var i = 0; i < jsonArray.length; i++){
			var o = jsonArray[i];
			var tr = document.createElement("tr");
			tr.innerHTML = 
				  '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.DUE_BILL_NO)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.CUUR_CD)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.LOAN_AMT)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.LOAN_BAL)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.REC_ABLE_INT_RMB)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.SUM_PAY_MONEY)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.ACTUAL_RATE)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.LOAN_STATUS)+'"/> </td>'
			tbody.appendChild(tr);
		}
	}
}

//信用卡
function loadXinyongkaData(obj){
	var jsonArray = obj.evalJSON();
	var length = jsonArray.length;
	if(length > 0){
		var tbody = document.getElementById("tbody");
		for(var i = 0; i < jsonArray.length; i++){
			var o = jsonArray[i];
			var tr = document.createElement("tr");
			tr.innerHTML = 
				  '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.CUUR_CD)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.PROD_TYPE)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.LOAN_BAL)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.POINT_BAL)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.CURR_TERM_UNPAY_BAL)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.BILL_DATE)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.RECYCLE_ST_LIMIT_BAL)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.ACCT_STAT_CD)+'"/> </td>';
			tbody.appendChild(tr);
		}
	}
}

function loadJindaitongData(obj){
	var jsonArray = obj.evalJSON();
	var length = jsonArray.length;
	if(length > 0){
		var tbody = document.getElementById("tbody");
		for(var i = 0; i < jsonArray.length; i++){
			var o = jsonArray[i];
			var tr = document.createElement("tr");
			tr.innerHTML = 
				  '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.DUE_BILL_NO)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.LOAN_AMT)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.LOAN_BAL)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.REC_ABLE_INT_RMB)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.SUM_PAY_MONEY)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.ACTUAL_RATE)+'"/> </td>'
				+ '<td style="width: 10%;"> <input type="text" readonly="readonly" class="inputvalue" value="'+setValue(o.LOAN_STATUS)+'"/> </td>';
			tbody.appendChild(tr);
		}
	}
}