import { CrudItemCoordinate } from './crud-item-coordinate';
import { CrudItemMap } from './crud-item-map';

export class CrudItemMapUpdate {
    constructor(
        public group: CrudItemMap,
        public addCoordinates: CrudItemCoordinate[],
        public removeCoordinateIds: number[]) { }
}
