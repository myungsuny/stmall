<template>

    <v-data-table
        :headers="headers"
        :items="searchMyPage"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'SearchMyPageView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            searchMyPage : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/searchMyPages'))

            temp.data._embedded.searchMyPages.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.searchMyPage = temp.data._embedded.searchMyPages;
        },
        methods: {
        }
    }
</script>

