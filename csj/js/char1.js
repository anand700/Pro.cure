var x="Ohio";
var chart1_click="Ohio";




					
					var width=400,
						height=400,
						radius=200,
						colors=d3.scale.category20c();
						
					var piedata = [{label:"Vivek", value:50},{label:"Aani", value:50},{label:"Pav", value:50}]
					
					var pie = d3.layout.pie()
								.value(function(d) {
								 return d.value;
								 });
								 
					var arc=d3.svg.arc()
							.outerRadius(radius);
							
					var pieChart= d3.select("#pie1").append('svg')
									.attr('width',width)
									.attr('height',height)
									.append('g')
									.attr('transform','translate('+(width-radius)+','+(height-radius)+')')
									.selectAll('path').data(pie(piedata))
									.enter().append('g')
										.attr('class','slice');
										
					
						pieChart.transition().ease('sin');			
										
										
					var slices = d3.selectAll('g.slice')
								 .append('path')
										.attr('fill',function(d,i) {
											return colors(i);
										
										})
								 .attr('d',arc);
								 
					var text = d3.selectAll('g.slice')
								.append('text')
								.text(function(d,i){
										return d.data.label;
								})
								.attr('text-anchor','middle')
								.attr('fill','white')
								.attr('transform',function(d){
									d.innerRadius=0;
									d.outerRadius=radius;
									return 'translate('+ arc.centroid(d)+')'
								});
		












/*			
			var graph2="#graph2";
		d3.csv('data/USA 4.csv', function(data) {
		   
			var population2=[];
			var intvalue	

			for (key in data) 
			{
				if(data[key].name==x && data[key].age>12 && data[key].sex==0 && data[key].age<85)
				{ 
					
					intvalue=+data[key].POPEST2012_CIV;
					population2.push(intvalue);
				}
					  
			}
			
				console.log(d3.max(population2));
				
				 console.log(population2);
					 
							var margin = {top:30, right:0, bottom:5,left:62}

							var height = 400-margin.top-margin.bottom,
								width = 1500-margin.right-margin.left,
								barWidth = 50,
								barOffset=5;
								
							var yScale= d3.scale.linear()
									.domain([0,d3.max(population2)])
									.range([0,height]);
									
							var	xScale = d3.scale.ordinal()
										.domain(d3.range(0,population2.length))
										.rangeBands([0,width],0.1);
										
							var colors =d3.scale.linear()
										.domain([0,d3.max(population2)])
										.range(['#3399FF','#0000FF']);


							var myChart2= d3.select(graph2).append('svg')
										.style('background','#18BC9C')
										.attr('width',width+margin.left+margin.right)
										.attr('height',height+margin.top+margin.bottom)
										.append('g')
										.attr('transform','translate('+margin.left+','+(margin.bottom)+')')			
										.selectAll('rect').data(population2)
										.enter().append('rect')
											.style('fill',colors)
											.attr('width',xScale.rangeBand())
											.attr('height',0)
											.attr('x',function(d,i) {return xScale(i);})
											.attr('y',height)
											
							myChart2.transition()
							.attr('height',function(d){return yScale(d);})
							.attr('y', function(d){return (height- yScale(d));})
							.duration(500)
							.ease('sin')

							var vGuideScale = d3.scale.linear()
											.domain([0,d3.max(population2)])
											.range([height,0])

							var vAxis=d3.svg.axis()
										.scale(vGuideScale)
										.orient('left')
										.ticks(10);
										
							var vGuide = d3.select('#graph2').select('svg').append('g');
										vAxis(vGuide)
										vGuide.attr('transform','translate('+(margin.left)+','+(margin.top-34)+')');
										vGuide.selectAll('path')
										.style({ fill: 'none', stroke: "#000"})
										vGuide.selectAll('line')
										.style({stroke:"#000"})

							var hAxis = d3.svg.axis()
								.scale(xScale)
								.orient('bottom')
								.ticks(xScale);

							var hGuide = d3.select('#graph2').select('svg').append('g')
								hAxis(hGuide)
								hGuide.attr('transform', 'translate('+(margin.left-5)+','+(height+margin.top-30)+')')
								hGuide.selectAll('path')
									.style({ fill: 'none', stroke: "#000"})
								hGuide.selectAll('line')
									.style({ stroke: "#000"})
										


								});			
				</script> 

<script>
		
			var population3=[];
		d3.csv('data/USA 4.csv', function(data) {
		   
			population3=[];
			for (key in data) 
			{
				if(data[key].name==x && data[key].age>12 && data[key].sex==0 && data[key].age<85)
				{ 
					
					population3.push(data[key].POPEST2013_CIV);
				}
					   
			}

							var margin = {top:30, right:0, bottom:5,left:62}

							var height = 400-margin.top-margin.bottom,
								width = 1000-margin.right-margin.left,
								barWidth = 50,
								barOffset=5;
								
							var yScale= d3.scale.linear()
									.domain([0,d3.max(population3)])
									.range([0,height]);
									
							var	xScale = d3.scale.ordinal()
										.domain(d3.range(0,population3.length))
										.rangeBands([0,width],0.1);
										
							var colors =d3.scale.linear()
										.domain([0,d3.max(population3)])
										.range(['#3399FF','#0000FF']);


							var myChart2= d3.select('#graph3').append('svg')
										.style('background','#18BC9C')
										.attr('width',width+margin.left+margin.right)
										.attr('height',height+margin.top+margin.bottom)
										.append('g')
										.attr('transform','translate('+margin.left+','+(margin.bottom+10)+')')			
										.selectAll('rect').data(population3)
										.enter().append('rect')
											.style('fill',colors)
											.attr('width',xScale.rangeBand())
											.attr('height',yScale)
											.attr('x',function(d,i) {return xScale(i);})
											.attr('y',height)
											
							myChart2.transition()
							.attr('height',function(d){return yScale(d);})
							.attr('y', function(d){return height- yScale(d);})
							.duration(500)
							.ease('sin')

							var vGuideScale = d3.scale.linear()
											.domain([0,d3.max(population3)])
											.range([height,0])

							var vAxis=d3.svg.axis()
										.scale(vGuideScale)
										.orient('left')
										.ticks(10);
										
							var vGuide = d3.select('#graph3').select('svg').append('g');
										vAxis(vGuide)
										vGuide.attr('transform','translate('+(margin.left)+','+(margin.top-10)+')');
										vGuide.selectAll('path')
										.style({ fill: 'none', stroke: "#000"})
										vGuide.selectAll('line')
										.style({stroke:"#000"})

							var hAxis = d3.svg.axis()
								.scale(xScale)
								.orient('bottom')
								.ticks(xScale);

							var hGuide = d3.select('#graph3').select('svg').append('g')
								hAxis(hGuide)
								hGuide.attr('transform', 'translate('+(margin.left-5)+','+(height+margin.top-11)+')')
								hGuide.selectAll('path')
									.style({ fill: 'none', stroke: "#000"})
								hGuide.selectAll('line')
									.style({ stroke: "#000"})
										


								});			