<template>
    <div>
        <button id="connect" class="btn btn-default" type="submit" :disabled="connected === true" @click.prevent="connect">Connect</button>
        <button id="disconnect" class="btn btn-default" type="submit" :disabled="connected === false" @click.prevent="disconnect">Disconnect</button>
        <button v-on:click="send">Send</button>
        <label>
            <input type="text" v-model="send_message" v-on:keyup.enter="send"/>
        </label>
        <div class="players-section">
            <p v-for="player in players"> {{ player.name }} </p>
        </div>
    </div>
</template>

<script>
    import SockJS from "sockjs-client";
    import Stomp from "webstomp-client";

    export default {
        name: "websocketdemo",
        data() {
            return {
                received_messages: [],
                send_message: null,
                connected: false,
                players: []
            };
        },
        methods: {
            send() {
                console.log("Send message:" + this.send_message);
                if (this.stompClient && this.stompClient.connected) {
                    const msg = { name: this.send_message };
                    this.stompClient.send("/app/join", JSON.stringify(msg), {});
                }
            },
            connect() {
                this.socket = new SockJS("http://localhost:8080/gs-guide-websocket");
                this.stompClient = Stomp.over(this.socket);
                this.stompClient.connect(
                    {},
                    frame => {
                        this.connected = true;
                        console.log(frame);
                        this.stompClient.subscribe("/fantasy/players/", tick => {
                            console.log(tick);
                            console.log("Dette: " + tick.body);
                        this.players = JSON.parse(tick.body)['playerList'];
                        });
                    },
                    error => {
                        console.log(error);
                        this.connected = false;
                    }
                );
            },
            disconnect() {
                if (this.stompClient) {
                    this.stompClient.disconnect();
                    this.players = [    ]
                }
                this.connected = false;
            },
            tickleConnection() {
                this.connected ? this.disconnect() : this.connect();
            }
        },
        mounted() {
            // this.connect();
        }
    };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
