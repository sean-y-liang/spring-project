FROM ubuntu:latest
LABEL authors="sean"

ENTRYPOINT ["top", "-b"]

# Stage 1: Build stage
FROM node:16-alpine as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Stage 2: Serve the static files
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
