function loadData(s1,s2,s3,s4,s5,s6,s7,s8,flag){
	if(s1 != ''){
		document.getElementById('model1').style.display = "block";
		document.getElementById('table1').innerHTML = s1;
	}
	if(s2 != ''){
		document.getElementById('model2').style.display = "block";
		document.getElementById('table2').innerHTML = s2;
	}
	if(s3 != '' && null != s3 && "null" != s3){
		document.getElementById('model3').style.display = "block";
		var jsonArray = s3.evalJSON();
		var zcList = jsonArray.zcList;
		var fzList = jsonArray.fzList;
		
		if(undefined != zcList){
			var zctr;
			var zctd1,zctd2,zctd3;
			if(zcList.length>0){
				for(var i=0;i<zcList.length;i++){
					var obj = zcList[i];
					zctr = document.createElement("tr");
					
					zctd1 = document.createElement("td");
					zctd2 = document.createElement("td");
					zctd3 = document.createElement("td");
					
					zctd1.innerHTML = '<input type="text" readonly="readonly" class="titletable_inputvalue" value="'+obj.PATypeName+'"/>';
					zctd2.innerHTML = '<input type="text" readonly="readonly" class="titletable_inputvalue" value="'+obj.PICurrencyName+'"/>';
					zctd3.innerHTML = '<input type="text" readonly="readonly" class="titletable_inputvalue" value="'+obj.PIBalance+'"/>';
					
					zctr.appendChild(zctd1);
					zctr.appendChild(zctd2);
					zctr.appendChild(zctd3);
					
					document.getElementById('tbody3_1').appendChild(zctr);
				}
			}
		}
		
		if(undefined != fzList){
			var fztr;
			var fztd1,fztd2,fztd3,fztd4,fztd5;
			
			if(fzList.length>0){
				for(var i=0;i<fzList.length;i++){
					var obj = fzList[i];
					fztr = document.createElement("tr");
					
					fztd1 = document.createElement("td");
					fztd2 = document.createElement("td");
					fztd3 = document.createElement("td");
					fztd4 = document.createElement("td");
					fztd5 = document.createElement("td");
					
					fztd1.style.width = "20%";
					fztd2.style.width = "20%";
					fztd3.style.width = "20%";
					fztd4.style.width = "20%";
					fztd5.style.width = "20%";
					
					fztd1.innerHTML = '<input type="text" readonly="readonly" class="titletable_inputvalue" value="'+obj.POCurrencyName+'"/>';
					fztd2.innerHTML = '<input type="text" readonly="readonly" class="titletable_inputvalue" value="'+obj.POBalance+'"/>';
					fztd3.innerHTML = '<input type="text" readonly="readonly" class="titletable_inputvalue" value="'+obj.Plimit+'"/>';
					fztd4.innerHTML = '<input type="text" readonly="readonly" class="titletable_inputvalue" value="'+obj.PPM+'"/>';
					fztd5.innerHTML = '<input type="text" readonly="readonly" class="titletable_inputvalue" value="'+obj.PPATypeName+'"/>';
					
					fztr.appendChild(fztd5);
					fztr.appendChild(fztd1);
					fztr.appendChild(fztd2);
					fztr.appendChild(fztd3);
					fztr.appendChild(fztd4);
					
					document.getElementById('tbody3_2').appendChild(fztr);
				}
			}
		}
	}
	if(s4 != ''){
		document.getElementById('model4').style.display = "block";
		document.getElementById('table4').innerHTML = s4;
	}
	if(s5 != ''){
		document.getElementById('model5').style.display = "block";
		document.getElementById('table5').innerHTML = s5;
	}
	if(s6 != ''){
		document.getElementById('model6').style.display = "block";
		document.getElementById('table6').innerHTML = s6;
	}
	if(s7 != ''){
		document.getElementById('model7').style.display = "block";
		document.getElementById('table7').innerHTML = s7;
		loadHint(flag);
	}
	if(s8 != ''){
		document.getElementById('model8').style.display = "block";
		document.getElementById('table8').innerHTML = s8;
	}
}