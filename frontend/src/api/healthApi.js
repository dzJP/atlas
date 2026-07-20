import api from "./axios";

export async function getHealth() {
    const response = await api.get("/api/health");
    return response.data;
}