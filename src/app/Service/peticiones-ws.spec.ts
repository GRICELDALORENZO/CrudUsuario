import { TestBed } from '@angular/core/testing';

import { PeticionesWS } from './peticiones-ws';

describe('PeticionesWS', () => {
  let service: PeticionesWS;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PeticionesWS);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
