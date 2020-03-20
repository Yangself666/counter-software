var app = new Vue({
	el: '#app',
	data: {
		chartSettings: {
			xAxisType: 'time'
		},
		chartData: [],
		devices: [],
		isEmpty:[],
		visible: false,
		pickerOptions: {
			shortcuts: [{
				text: '最近一周',
				onClick(picker) {
					const end = new Date();
					const start = new Date();
					start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
					picker.$emit('pick', [start, end]);
				}
			}, {
				text: '最近一个月',
				onClick(picker) {
					const end = new Date();
					const start = new Date();
					start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
					picker.$emit('pick', [start, end]);
				}
			}, {
				text: '最近三个月',
				onClick(picker) {
					const end = new Date();
					const start = new Date();
					start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
					picker.$emit('pick', [start, end]);
				}
			}]
		},
		value: ''
	},
	methods: {
		commit: function(value) {
			var that = this;
			axios.get('http://yangself.cn:549/counter/devices').then(function(response) {
				that.devices = response.data;
				axios.get('http://yangself.cn:549/counter/historyCounts?pre='+value[0]+"&suf="+value[1]).then(function(res) {
					console.log(res.data);
					that.chartData = res.data[0];
					that.isEmpty = res.data[1];
				}).catch(function(error) {
					console.log(error)
				})
			}).catch(function(error) {
				console.log(error)
			})
		}
	},
	components: {
		VeLine
	}
})
