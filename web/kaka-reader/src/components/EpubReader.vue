<template>
    <div class="epub-reader">
        <div ref="viewer"></div>
    </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import ePub, {Rendition} from 'epubjs';

@Component
export default class EpubReader extends Vue {
    private mounted() {
        const book = ePub('/path/to/your/book.epub');

        const rendition: Rendition = book.renderTo(this.$refs.viewer, {
            manager: 'continuous'
        });

        rendition.display();
    }
}
</script>

<style scoped>
.epub-reader {
    width: 100%;
    height: 100%;
}
</style>
