import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: 'ID',
        accessor: 'id',
    },

    {
        Header: 'Name',
        accessor: 'name',
    },
    {
        Header: 'BIRTHDATE',
        accessor: 'birthDate',
    },
    {
        Header: 'GENDER',
        accessor: 'gender',
    },
    {
        Header: 'ADDRESS',
        accessor: 'address',
    },

];

const filters = [
    {
        accessor: 'id',
    }
];

class CaregiverTable extends React.Component {

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

export default CaregiverTable;