import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  FilterOrdersCockpit,
  PredictionCriteria,
} from 'app/shared/backend-models/interfaces';
import { Observable } from 'rxjs';
import { exhaustMap, map } from 'rxjs/operators';
import { ConfigService } from '../../core/config/config.service';
import { OrdersData, DishOrdersData, } from '../../shared/view-models/interfaces';

@Injectable()
export class PredictionService {
  private readonly predictionRestPath: string =
    'predictionmanagement/v1/nextweek';
  private readonly getOrdereddishesRestPath: string =
    'ordermanagement/v1/ordereddishes/history';

  private readonly restServiceRoot$: Observable<
    string
  > = this.config.getRestServiceRoot();

  constructor(private http: HttpClient, private config: ConfigService) {}

  getPrediction(
    predictionCriteria: PredictionCriteria,
  ): Observable<OrdersData> {
	  
	var dates: Date[] = [];
    var holidays: string[] = [];
    var weather: number[] = [];
    var dishes: DishOrdersData[]  = [];
	
	let i: number = 0;
	
    return this.restServiceRoot$.pipe(
      exhaustMap((restServiceRoot) =>
        this.http
          .post(
            `${restServiceRoot}${this.predictionRestPath}`,
            predictionCriteria,
          )
          .pipe(
            map((res: any) => {
				
		      const startDate = Date.parse(predictionCriteria.startBookingdate);
              const { data } = res;
              const dishesreduced = data.reduce((acc, row) => {
                acc[row.dishId] = row.dishName;
                return acc;
              }, {});
				
				dates= Array(7)
                  .fill(null)
                  .map((_, i) => {
                    const result = new Date(startDate);
                    result.setDate(result.getDate() + i);
                    return result;
                  });
                holidays = predictionCriteria.holidays;
                weather = predictionCriteria.temperatures.map((x) => +x);
				
				console.log("dates",dates)
				console.log("holidays",holidays)
				console.log("weather",weather)
				
				res.data.forEach((record) => {
				const dish = dishes.filter(
                  (value) => value.id === record.dishId,
                );
				if (dish.length === 0) {
			    dishes.push({
                    id: record.dishId,
                    name: record.dishName,
                    orders: [Math.max(record.forecast,0)],
                  });
				}else{
					dish[0].orders.push(Math.max(record.forecast,0));
				}
				});
				dishes.sort((x, y) => x.id - y.id);
				console.log("dishes",dishes)
				return {dates, holidays, weather, dishes,};
				
            }),
		
          ),
      ),
    );
  }

 getOrders(filters: FilterOrdersCockpit): Observable<OrdersData> {
  
	var dates: Date[] = [];
    var holidays: string[] = [];
    var weather: number[] = [];
    var dishes: DishOrdersData[]  = [];
	
	let i: number = 0;
	
    return this.restServiceRoot$.pipe(
      exhaustMap((restServiceRoot) =>
        this.http
          .post(`${restServiceRoot}${this.getOrdereddishesRestPath}`, filters)
          .pipe(
            map((data: any) => {
				
              data.content.forEach((record) => {
				  
				 
               const dish = dishes.filter(
                  (value) => value.id === record.dish.id,
                );

                if (dish.length === 0) {
                  dishes.push({
                    id: record.dish.id,
                    name: record.dish.name,
                    orders: [record.orderedDishes.amount],
                  });
                } else {
                  dish[0].orders.push(record.orderedDishes.amount);
                }

                const date = dates.filter(
                  (value) =>
                    value.toLocaleDateString('en-GB') ===
                    new Date(
                      record.orderedDishes.bookingdate,
                    ).toLocaleDateString('en-GB'),
                );

                if (date.length === 0) {
				  let temperature: number = record.orderedDishes.temperature;
				  let designation: string = record.orderedDishes.designation;
				  let bookingdate: Date = new Date(record.orderedDishes.bookingdate);  
				  
				  dates[i] = bookingdate;
				  weather[i] = temperature;
				  holidays[i] =  designation;
				  i++;
				  
                 
                }
				
              });
			
			  
              // sort the orders
              dishes.sort((x, y) => x.id - y.id);

              const metadata = dates.reduce((accum, date, index) => {
                accum.push({
                  date,
                  weather: weather[index],
                  holiday: holidays[index],
                });
                return accum;
              }, []);

              metadata.sort((x, y) => x.date - y.date);
		
            
				
              return {dates, holidays, weather, dishes,};
            }),
          ),
      ),
    );
  }
}
