// 기본 구성
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth'
	});
	calendar.render();
});

// db 연동
$(function() {
	var calendarEl = document.getElementById('calendar'); // calendarEl 변수를 이 위치로 이동
	var request = $.ajax({
		url: "/production/monthPlan",
		method: "GET",
		dataType: "json"
	});

	request.done(function(data) {
		console.log(data);

		// FullCalendar 이벤트로 변환
		var events = data.map(function(item) {
			return {
				start: item.expectedProductionStartDate,
				end: item.expectedProductionEndDate,
				title: item.productionPlanCode
			};
		});
		var events = [
			{
				start: '2024-01-19',
				end: '2024-01-19',
				title: '휴관일'
			},
			{
				start: '2024-01-25',
				end: '2024-01-28',
				title: '문화의날 행사'
			},
			{
				start: '2024-02-06',
				end: '2024-02-06',
				title: 'KG도서관 발표일'
			}
			// 여러 이벤트를 추가할 수 있습니다.
			// {
			//   start: '다른 이벤트의 시작일',
			//   end: '다른 이벤트의 종료일',
			//   title: '다른 이벤트의 제목'
			// },
			// ...
		];
		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialView: 'dayGridMonth',
			headerToolbar: {
				left: 'prev,next today',
				center: 'title',
				right: 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			events: events
		});

		calendar.render();
	});

	request.fail(function(jqXHR, textStatus) {
		alert("Request failed: " + textStatus);
	});
});