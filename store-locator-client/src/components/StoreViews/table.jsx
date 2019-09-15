import React, { Component } from 'react'

class Table extends Component {
    render() {
        return (
            <div>
                <br />
                <h4>Find Nearby Stores Below: </h4>
                <br />
                <table className="table">
                    <thead>
                        <tr>
                            <th>Store Name</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Open Time</th>
                            <th>Close Time</th>
                            <th>Distance</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.props.stores.map((store, i) =>
                                <tr key={store.id}>
                                    <td>{store.addressName}</td>
                                    <td>{store.latitude}</td>
                                    <td>{store.longitude}</td>
                                    <td>{store.openTime}</td>
                                    <td>{store.closeTime}</td>
                                    <td>{store.distance}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Table;
