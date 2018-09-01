/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { CountryImDetailComponent } from 'app/entities/country-im/country-im-detail.component';
import { CountryIm } from 'app/shared/model/country-im.model';

describe('Component Tests', () => {
    describe('CountryIm Management Detail Component', () => {
        let comp: CountryImDetailComponent;
        let fixture: ComponentFixture<CountryImDetailComponent>;
        const route = ({ data: of({ country: new CountryIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [CountryImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CountryImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CountryImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.country).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
