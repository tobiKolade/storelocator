import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react'
// import GoogleMapReact from 'google-map-react';

const StorePin = ({ text }) => <div style={{ color: "red" }}>{text}</div>;


class Nmap extends Component {
    static defaultProps = {
        center: {
            lat: 51.28,
            lng: 3.44
        },
        zoom: 7
    };



    render() {

        let center = {
            lat: +this.props.latitude,
            lng: +this.props.longitude
        };

        let storePins = this.props.stores.map((store, index) => {
            if (store.latitude === null || store.longitude === null) {
                return null
            } else {
                const lati = +store.latitude;
                const long = +store.longitude;
                return <StorePin
                    key={store.id}
                    lat={lati}
                    lng={long}
                    text={store.addressName}

                />
            }
        });

        return (
            <div style={{ height: '100vh', width: '100%' }}>

                <GoogleMapReact
                    bootstrapURLKeys={{ key: 'AIzaSyB4bGCRkmXnUub9mUZ3xZvSQvxU3-6SVQY' }}
                    defaultCenter={this.props.center}
                    center={center}
                    defaultZoom={this.props.zoom}
                    onChildMouseEnter={this.onChildMouseEnter}
                    onChildMouseLeave={this.onChildMouseLeave}
                >

                    <StorePin
                        lat={51.275006}
                        lng={3.444601}
                        text="umbo Aardenburg Ingels"
                    />

                    {storePins}


                </GoogleMapReact>
            </div>
        )
    }
}

export default Nmap;
