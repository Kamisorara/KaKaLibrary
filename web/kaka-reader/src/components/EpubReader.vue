<template>
    <div class="ebook">
        <div ref="viewer"></div>
        <div class="read-wrapper">
            <!--        渲染的位置-->
            <div id="read"></div>
            <p>当前页码:{{ progress }}</p>
            <div class="mask">
                <div class="left" @click="prevPage"></div>
                <!--            <div class="center" @click="toggleTitleAndMenu"></div>-->
                <div class="right" @click="nextPage"></div>
            </div>
        </div>
    </div>
</template>

<script>
import Epub from 'epubjs';

export default {
    data() {
        return {
            progress: 0,
            bookAvailable: false
        }
    },
    methods: {
        // 跳转当前位置
        getCurrentLocation() {
            if (this.rendition) {
                this.showProgress()
            }
        },
        // 根据链接跳转到指定位置
        jumpTo(href) {
            this.rendition.display(href).then(() => {
                this.showProgress()
            })
            this.hideTitleAndMenu()
        },
        onProgressChange(progress) {
            const percentage = progress / 100
            const location = percentage > 0 ? this.locations.cfiFromPercentage(percentage) : 0
            this.rendition.display(location)
        },
        // 进度条跳转更新
        showProgress() {
            // 获取当前位置信息
            const currentLocation = this.rendition.currentLocation()
            // 获取当前位置进度百分比
            this.progress = this.bookAvailable ? this.locations.percentageFromCfi(currentLocation.start.cfi) : 0
            // 转化成0-100的数字
            this.progress = Math.round(this.progress * 100)
        },
        prevPage() {
            // Rendition.prev
            if (this.rendition) {
                this.rendition.prev().then(() => {
                    this.showProgress();
                })
            }
        },
        nextPage() {
            // Rendition.next
            if (this.rendition) {
                this.rendition.next().then(() => {
                    this.showProgress();
                })
            }
        },
    },
    mounted() {
        this.book = new Epub('/epub/demo.epub');
        this.rendition = this.book.renderTo('read', {
            width: window.innerWidth,
            height: window.innerHeight
        });
        // 通过Rendition.display渲染电子书
        this.rendition.display();
        // 获取locations对象 epubjs的钩子函数实现
        this.book.ready.then(() => {
            this.navigation = this.book.navigation
            return this.book.locations.generate()
        }).then(result => {
            console.log(result);
            this.locations = this.book.locations
            this.bookAvailable = true
        })
    },
}
</script>

<style scoped>
/* 样式可以根据需要自定义 */
.epub-viewer {
    width: 100%;
    height: 100%;
    overflow: auto;
}

.ebook {
    position: relative;
}

.mask {
    position: absolute;
    display: flex;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 9;
}

.left {
    margin-left: 60px;
    height: 900px;
    width: 900px;

}

.right {
    margin-left: 70px;
    height: 900px;
    width: 900px;
}
</style>
