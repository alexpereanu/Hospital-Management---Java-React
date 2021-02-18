
class WebSocket extends React.Component {

    constructor() {
        super();
        this.state = {
            endpoint: "ws://localhost:8080/activity",
            messages: []
        }
    }

    componentDidMount() {
        //initialize connection
        const ws = new WebSocket(this.state.endpoint)
        ws.onopen = () => {
            //send any msg from Client if needed

        }
        //save whatever response from client
        ws.onmessage = evt => {
            this.setState({
                message: this.state.message.concat(evt.data)
            })
        }
    }

    render() {
        return (
            <div>
                <p>{this.state.message.map(items => <li>{items}</li>)}</p>
            </div>
        )
    }
}