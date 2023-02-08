


<template>
  <!-- category
  title
  writer
  difficulty
  totalTime
  nutritionInfo.calories -->
    <nuxt-link :to="'/recipe/' +item.id " class="text-decoration-none">
        <v-card elevation="0" outlined class="br-10 o-hidden">
            <div class="position-relative">


                <v-img  v-if="item.photo_url" width="100%" class="btr-10" :src="item.photo_url"></v-img>
                <v-img  v-if="!item.photo_url" width="100%" class="btr-10" :src="require(`~/assets/images/food/default.jpg`)"></v-img>
                <div>
                    <v-chip
                        label
                        flat
                        class="p-absolute top-0 rounded-r-xl rounded-l-0 mt-3"
                        color="primary"
                    >{{item.category.description}}</v-chip>
                </div>
            </div>

            <div class="pa-4 d-flex justify-space-between align-center">
                <span class="d-inline-block text-truncate me-3 f-600 text-18 card-title">{{ item.name }}</span>
                <div class="d-flex align-center card-rating">
                    <v-icon small color="yellow darken-2" class="me-1">mdi-star</v-icon>
                    <div class="">
                        <span class="font-weight-bold text-14">{{item.totalTime}}</span>
                        <!-- <span class="font-weight-bold text-14">({{item.preparationTime}}</span> -->
                        <!-- <span class="font-weight-bold text-14">/{{item.cookingTime}})</span> -->
                    </div>

                </div>
            </div>
            <p class="px-4 text--darken-1">
              {{item.title}}
            </p>
            <p class="px-4 grey--text text--darken-1">
              {{item.writer}}
            </p>
            <p class="px-4 grey--text text--darken-1">
              <span class="primary--text f-600 me-1">{{item.nutritionInfo.calories}} kcal</span>
               • {{ item.nutritionInfo.protein }} protein,
               {{ item.nutritionInfo.fat }} fat,
               {{ item.nutritionInfo.carbohydrates }} carbohydrates
            </p>


        </v-card>
    </nuxt-link>

</template>

<script>
export default {
  props: ['item']
}
</script>

<style lang="scss">
    .card-title {
        flex: 1 1 auto;
        max-width: 100%;
    }
    .card-rating {
        flex: 0 0 auto;
    }
    .b-none {
        border: none !important;
    }
    btr-10 {
        border-top-left-radius: 10px !important;
        border-top-right-radius: 10px !important;
    }
</style>
