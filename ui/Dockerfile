FROM node:8.11.3 AS BUILD_IMAGE

RUN npm install npm@6.1.0

ENV APP_HOME=/usr/dev/ui/
WORKDIR $APP_HOME

COPY package.json package-lock.json ./

RUN npm install

#copy UI code
COPY angular.json tsconfig.json ./
COPY src ./src/

RUN npm run build --prod=true


FROM nginx:alpine

COPY etc/nginx.conf /etc/nginx/nginx.conf

COPY --from=BUILD_IMAGE /usr/dev/ui/dist/ /usr/share/nginx/html/
