/**
 * 
 */
// 	초기화 작업
// 		1. 세션 만료 시간 (formatting : 2:00)
// 		2. 1초 단위 스케쥴링
// 		3. 메시지 히든 처리, 1분남은 시점에 show 
// 	반복 형태 작업
// 		2. 1초 단위 스케쥴링
// 		   timer==0 
//  이벤트 처리 : 응/아니오 버튼
	$.timeFormat = function(timeValue){
		var min = Math.trunc(timeValue/60);
		var sec = timeValue % 60;
		return min+":"+sec;
	}
	
	var timer = -1;
	var timerArea;
	var msgArea;
	var ctrlBtn;
	var msgJob = null;
	var timerJob = null;
	
	$.fn.uiInit=function(uiAll){
		 timerArea = this;
		 msgArea = uiAll.msgArea.hide();
		 ctrlBtn = uiAll.ctrlBtn.on("click", function(event){
				var btnId = $(this).prop("id");
				if(btnId==uiAll.yesBtnId){
					if(msgJob!=null){
						clearTimeout(msgJob);
						msgJob = null;
					}
					init();	
				}
				msgArea.hide();
			});
	}
	
	function init(timeout){
		timer = timeout;
		if(timerJob==null){
			timerJob = setInterval(function(){
				timer--;
				if(timer<=0){
					clearInterval(timerJob);
				}else{
					timerArea.html($.timeFormat(timer));
				}
			}, 500);
		}
		if(msgJob==null){
			msgJob = setTimeout(function(){
				msgArea.show();
			}, (timeout-60)*500);
		}
	}
	