var app = new Vue({
	el: '#app',
	data: {
		chartSettings: {
			xAxisType: 'time'
		},
		chartData: [],
		devices: [],
		isEmpty:[]
	},
	created: function() {
		this.onLoad();
		const timer = setInterval(() => {
			console.log("触发定时器");
			this.onLoad();
		}, 30000);
		// 在beforeDestroy钩子触发时清除定时器
		this.$once('hook:beforeDestroy', () => {
			clearInterval(timer);
		})
	},
	methods: {
		onLoad: function() {
			var that = this;
			axios.get('http://yangself.cn:549/counter/devices').then(function(response) {
				that.devices = response.data;
				axios.get('http://yangself.cn:549/counter/counts').then(function(res) {
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
