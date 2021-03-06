$(document).ready(function(){
var s1 = [3,3,3,3,3];
var s2 = [1,1,1,1,1];
var s3 = [0,0,0,0,0];
var ticks = [19,20,21,22,23];plot2 = $.jqplot('chart', [s1, s2,s3], {animate:true,
seriesDefaults: { renderer:$.jqplot.BarRenderer, pointLabels: { show: true  } },
series: [ { label: 'Passed Test Cases'  }, { label: 'Failed Test Cases'  }, { label: 'Skipped Test Cases'   }  ],
axes: { xaxis: {label: 'Executions', renderer: $.jqplot.CategoryAxisRenderer,
rendererOptions:{ varyBarColor : true },  ticks: ticks   },yaxis:{label: 'Test Cases'} },
grid: { background: '#ffffff',    drawGridLines: true,   gridLineColor: '#cccccc',   borderColor: '#cccccc', 
 borderWidth: 0.5,  shadow: true,  shadowOffset: 1,  shadowWidth: 0.2,  shadowDepth: 0.2 },
legend: { show: true, placement: 'outsideGrid',  location:'e' },
seriesColors: [ "#A2BF2F", "#BF2F2F", "#2F69BF"],
highlighter: {show: true}
});
$('chart').height($('#ParentElement').height() * 0.96);
$('chart').width($('#ParentElement').width() * 0.96);
});
