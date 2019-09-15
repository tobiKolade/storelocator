import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react'
// import GoogleMapReact from 'google-map-react';
import StorePin from './storePin';


class Nmap extends Component {
    static defaultProps = {
        center: {
            lat: 51.28,
            lng: 6.99
        },
        zoom: 11
    };



    render() {


        let center = {
            lat: this.props.stores.length > 0 ? +this.props.stores[0].latitude : 51.28,
            lng: this.props.stores.length > 0 ? +this.props.stores[0].longitude : 6.99
        };



        let storePins = this.props.stores.map((store, index) => {
            if (store.latitude === null || store.longitude === null) {
                return null
            } else {
                let open = store.openTime ? store.openTime.substring(0, 5) : 'Gesloten';
                let close = store.closeTime ? store.closeTime.substring(0, 5) : 'Gesloten';

                return <StorePin
                    key={store.id}
                    lat={store.latitude}
                    lng={store.longitude}
                    text={store.addressName}
                    open={open}
                    close={close}

                    item={'item_' + index}
                />
            }
        });

        return (
            <div style={{ height: '650px', width: '100%' }}>
                <GoogleMapReact
                    bootstrapURLKeys={{ key: 'AIzaSyB4bGCRkmXnUub9mUZ3xZvSQvxU3-6SVQY' }}
                    defaultCenter={this.props.center}
                    center={center}
                    defaultZoom={this.props.zoom}
                    onChildMouseEnter={this.onChildMouseEnter}
                    onChildMouseLeave={this.onChildMouseLeave}
                >

                    {storePins}


                </GoogleMapReact>

            </div>
        )
    }
}

export default Nmap;
