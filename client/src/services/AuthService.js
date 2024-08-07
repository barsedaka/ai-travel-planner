import axios from "axios";

const AUTH_REST_API_BASE_URL = 'http://localhost:8080/api/auth';

export const registerAPICall = (registerObj) => axios.post(AUTH_REST_API_BASE_URL + '/register', registerObj);ככ

export const loginAPICall = (usernameOrEmail, password) => axios.post(AUTH_REST_API_BASE_URL + '/login', { usernameOrEmail, password });

export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

export const saveLoggedInUser = (username) => {
  sessionStorage.setItem("authenticatedUser", username);
};

export const saveLoggedInUserRole = (role) => {
  sessionStorage.setItem("role", role);
};

export const getLoggedInUser = () => {
    return sessionStorage.getItem("authenticatedUser");
}

export const getLoggedInUserRole = () => {
  return sessionStorage.getItem("role");
};

export const logout = () => {
    localStorage.clear();
    sessionStorage.clear();
}

export const isAdminUser = () => {
    let role = sessionStorage.getItem("role");
    
    return role != null && role === 'ROLE_ADMIN';
}