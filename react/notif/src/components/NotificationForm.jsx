import React, {Fragment, useState} from "react";
import {useAlert} from "react-alert";
import {Form, Button} from "react-bootstrap";

const SMSForm = (props) => {
    const {to, body} = props;
    return (
        <Fragment>
            <Form.Group controlId="formToField">
                <Form.Label>To</Form.Label>
                <Form.Control type="text" name="to" placeholder="Mobile Number with Country Code" value={to}
                              onChange={props.onChange}/>
            </Form.Group>

            <Form.Group controlId="formBody">
                <Form.Label>Message</Form.Label>
                <Form.Control type="text" name="body" placeholder="Message" value={body} onChange={props.onChange}/>
            </Form.Group>
        </Fragment>
    );
}

const EmailForm = (props) => {
    const {to, title, body} = props;
    return (
        <Fragment>
            <Form.Group controlId="formToField">
                <Form.Label>To</Form.Label>
                <Form.Control type="email" name="to" placeholder="Email Address" value={to} onChange={props.onChange}/>
            </Form.Group>

            <Form.Group controlId="formTitle">
                <Form.Label>Subject</Form.Label>
                <Form.Control type="text" name="title" placeholder="Subject" value={title} onChange={props.onChange}/>
            </Form.Group>

            <Form.Group controlId="formBody">
                <Form.Label>Body</Form.Label>
                <Form.Control type="text" name="body" placeholder="Body" value={body} onChange={props.onChange}/>
            </Form.Group>
        </Fragment>
    );
}

const FirebaseForm = (props) => {
    const {to, title, body} = props;
    return (
        <Fragment>
            <Form.Group controlId="formToField">
                <Form.Label>To</Form.Label>
                <Form.Control type="text" name="to" placeholder="Firebase Registration Token" value={to}
                              onChange={props.onChange}/>
                <Form.Text className="text-muted">
                    <a href="/generate-firebase-token" target="_blank">Generate Firebase Registration Token</a>
                </Form.Text>
            </Form.Group>

            <Form.Group controlId="formTitle">
                <Form.Label>Title</Form.Label>
                <Form.Control type="text" name="title" placeholder="Title" value={title} onChange={props.onChange}/>
            </Form.Group>

            <Form.Group controlId="formBody">
                <Form.Label>Body</Form.Label>
                <Form.Control type="text" name="body" placeholder="Body" value={body} onChange={props.onChange}/>
            </Form.Group>
        </Fragment>
    );
}

const NotificationForm = () => {
    const channels = ['firebase', 'email', 'sms'];
    const triggerTypes = ['SYNC', 'ASYNC', 'PERIODIC'];
    const alert = useAlert();

    const [state, setState] = useState({
        type: channels[0],
        triggerType: triggerTypes[0],
        to: '',
        title: '',
        body: '',
        cronExpression: '',
    });

    const handleChange = e => {
        setState({
            ...state,
            [e.target.name]: e.target.value,
        });
    }

    let channelForm;
    if (state.type.toLowerCase() === 'sms') {
        channelForm = <SMSForm to={state.to} body={state.body} onChange={handleChange}/>
    } else if (state.type.toLowerCase() === 'firebase') {
        channelForm = <FirebaseForm to={state.to} title={state.title} body={state.body} onChange={handleChange}/>
    } else if (state.type.toLowerCase() === 'email') {
        channelForm = <EmailForm to={state.to} title={state.title} body={state.body} onChange={handleChange}/>
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('http://localhost:8080/notify', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(state),
        }).then(r => r.json())
            .then(data => {
                if (data.success) {
                    alert.success(data.message);
                    setState({
                        ...state,
                        to: '',
                        title: '',
                        body: '',
                        cronExpression: ''
                    })
                } else {
                    alert.error(data.message);
                }
            });
    };
    return (
        <Fragment>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formChannelType">
                    <Form.Label>Channel:&nbsp;</Form.Label>
                    {channels.map((type) => (
                        <Form.Check
                            key={type}
                            inline
                            name="type"
                            checked={state.type.toLowerCase() === type.toLowerCase()}
                            label={type}
                            onChange={handleChange}
                            style={{textTransform: "uppercase"}}
                            type="radio"
                            value={type}
                            id={`${type}-1`}
                        />
                    ))}
                </Form.Group>

                <Form.Group controlId="formTriggerType">
                    <Form.Label>Trigger Type:&nbsp;</Form.Label>
                    {triggerTypes.map((type) => (
                        <Form.Check
                            key={type}
                            inline
                            name="triggerType"
                            checked={state.triggerType.toLowerCase() === type.toLowerCase()}
                            label={type}
                            onChange={handleChange}
                            type="radio"
                            value={type}
                            id={`${type}-1`}
                        />
                    ))}
                </Form.Group>

                {channelForm}

                {
                    state.triggerType.toLowerCase() === 'periodic' &&
                    <Form.Group controlId="formCronExpression">
                        <Form.Label>Cron Expression</Form.Label>
                        <Form.Control type="text" name="cronExpression" placeholder="Cron Expression"
                                      onChange={handleChange}/>
                    </Form.Group>

                }

                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </Fragment>
    );
}

export default NotificationForm;