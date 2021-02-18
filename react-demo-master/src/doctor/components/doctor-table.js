import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: 'ID',
        accessor: 'id',
    },
    {
        Header: 'NAME',
        accessor: 'name',
    },
    {
        Header: 'GENDER',
        accessor: 'gender',
    }
];

const filters = [
    {
        accessor: 'id',
    }
];

class DoctorTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tableData: this.props.tableData
        };
    }

    render() {
        return (
            <Table
                data={this.state.tableData}
                columns={columns}
                search={filters}
                pageSize={5}
            />
        )
    }
}

export default DoctorTable;