import React, { Component } from 'react';
import ViewsContainer from '../StoreViews/viewsContainer';
import axios from 'axios';
import { API_BASE_URL } from '../../constants/index'

class Container extends Component {
    constructor(props) {
        super(props);

        this.state = {
            latitude: '',
            longitude: '',
            stores: []
        }

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(e) {
        axios({
            method: 'get',
            url: API_BASE_URL + '/stores/nearest?latitude=' + this.state.latitude + '&longitude=' + this.state.longitude
        })
            .then((response) => {
                this.setState({
                    stores: response.data
                });
            })
            .catch((err) => console.log(err));
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        return (
            <div>
                <h2 style={{ paddingTop: "30px", paddingBottom: "20px" }}>Jumbo Store Locator</h2>
                <div className="row">
                    <div className="col-md-4 form-group">
                        <input onChange={this.onChange} name="latitude" value={this.state.latitude} type="number" placeholder="Latitude" className="form-control" />
                    </div>
                    <div className="col-md-4 form-group ">
                        <input onChange={this.onChange} name="longitude" value={this.state.longitude} type="number" placeholder="Longitude" className="form-control" />
                    </div>
                    <div className="col-md-4 form-group">
                        <button className="btn btn-primary btn-block" onClick={this.onSubmit} >Find Nearby Stores </button>
                    </div>
                </div>
                <hr />

                <ViewsContainer
                    stores={this.state.stores}
                    longitude={this.state.longitude}
                    latitude={this.state.latitude}
                />
            </div>
        )
    }
}

export default Container;
;