<script setup>
import { onMounted, ref } from "vue";
import { getHealth } from "./api/healthApi";

const status = ref("Checking...");

onMounted(async () => {
  try {
    const response = await getHealth();
    status.value = response.status;
  } catch (error) {
    status.value = "Backend Offline";
    console.error(error);
  }
});
</script>

<template>
  <main>
    <h1>Atlas</h1>
    <p>Backend Status: {{ status }}</p>
  </main>
</template>

<style scoped>
main {
  padding: 2rem;
  font-family: Arial, sans-serif;
}
</style>