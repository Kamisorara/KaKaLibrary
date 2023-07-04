<template>
    <EpubReader/> <!-- Use the EpubReader component here -->
</template>

<script>
import EpubReader from './components/EpubReader.vue'

export default {
    components: {
        EpubReader
    },
}
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>
