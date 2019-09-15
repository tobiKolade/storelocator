import React, { Component } from 'react';
import Nmap from './nmap';
import Table from './table';

class ViewsContainer extends Component {
    render() {
        return (
            <div className="col-md-12">
                <nav>
                    <div className="nav nav-tabs" id="nav-tab" role="tablist">
                        <a className="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Map View</a>
                        <a className="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Table View</a>
                    </div>
                </nav>
                <div className="tab-content" id="nav-tabContent">
                    <div className="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                        <Nmap
                            stores={this.props.stores}
                            longitude={this.props.longitude}
                            latitude={this.props.latitude}
                        />
                    </div>
                    <div className="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                        <Table stores={this.props.stores} />
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewsContainer;
